package safariami.manager.job;


import safariami.manager.model.ActionLog;
import safariami.manager.model.ErrorLog;
import safariami.manager.model.Job;
import safariami.manager.model.Meter;
import safariami.manager.service.ActionLogService;
import safariami.manager.service.ErrorLogService;
import safariami.manager.service.JobService;
import safariami.manager.service.MeterService;
import safariami.manager.util.Constants;
import safariami.manager.util.SMPPHelper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class MonitorJob extends JobBase {

	private static Logger log = LoggerFactory.getLogger(MonitorJob.class);


	@Autowired
	private MeterService meterService;

	@Autowired
	private ErrorLogService errorLogService;

	@Autowired
	private ActionLogService actionLogService;
	
	@Autowired
	private JobService jobService;

	private List<String> smsSentList;

	@Scheduled(fixedDelay = (60 * 5) * 1000) // the correct line
	public void run() {
		log.info("RUNNING MONITOR JOBS");
		
		smsSentList = new ArrayList<String>();
		
		//1. Process action log
		processActionLog();
		
		//2. Process Jobs
		processJobs();
		
		//3. Process prepayment
		//processPrepaymentJobs();
		
		smsSentList.clear();
	}
	
	public void processPrepaymentJobs() {
		log.info("Processing prepayment jobs");
		List<Job> prepaymentJobs = jobService.findJobByStatusAndName(Constants.JobStatus.READY_TO_RUN.name(), 
				Constants.JobNames.DEMAND.name());
		
		if(prepaymentJobs == null || prepaymentJobs.size() < 1) {
			log.info("Prepayment job size: 0");
			return;
		}
		
		//Send SMS for each meter
		// Only prepayment meters
		List<Meter> meters = meterService.findByIsActiveAndIsPrepayment(true, true);
		if(meters != null) {
			for(Meter meter : meters) {
				
				// Creat action log
				for(Job job : prepaymentJobs) {
					ActionLog actionLog = new ActionLog(meter.getSerialNo(), meter.getPhoneNumber(), job.getObis(), 
							job.getType(),null, null, job.getId());

		            actionLogService.save(actionLog);
					job.setStatus(Constants.JobStatus.RUNNING.name());
					jobService.save(job);
				}
				
				if(smsSentList.contains(meter.getSerialNo())) {
					log.info("SMS already sent. Skipping:"+meter.getSerialNo() );
					continue;
				}

				SMPPHelper smppHelper = new SMPPHelper("MONITOR", meter.getSerialNo(), meter.getPhoneNumber());
				if (!smppHelper.sendSMSWithRetry()) {
					log.error("MONITOR: Unable to send SMS to: " + meter.getPhoneNumber());
					errorLogService.save(new ErrorLog(meter.getId(), "MONITOR", "Unable to send SMS to "+meter.getPhoneNumber()));
				}

			}
		}
	}
	
	private void processJobs() {
		log.info("Processing jobs");
		//1. Check if there are any actions left for running jobs and if not set their status to COMPLETED
		//2. Create action logs for READY_TO_RUN jobs and set their to RUNNING
		
		List<Job> runningJobs = jobService.findJobByStatus(Constants.JobStatus.RUNNING.name());
		if(runningJobs != null) {
			for(Job job : runningJobs) {
				int count;
				try {
					count = actionLogService.countActionLogsByJobIdAndCounterLessThan(job.getId(), 5);
					if(count < 1) {
						job.setStatus(Constants.JobStatus.COMPLETED.name());
						jobService.save(job);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
		
		List<Job> readyJobs = jobService.findJobByStatus(Constants.JobStatus.READY_TO_RUN.name());
		//1. Filter them (e.g MONTHLY overrides DAILY and HOURLY and DAILY overrides HOURLY)
		//2. Create action logs
		List<Job> filteredJobs = filterJobs(readyJobs);
		
        int batchSize = 500;
        int batchCount = 1;
        
		if(filteredJobs != null) {

			//Create action log for each job
			//Send SMS for each meter
			List<Meter> meters = meterService.findByIsActive(true);
			if(meters != null) {
				for(Meter meter : meters) {
					
					// Create action log
					for(Job job : filteredJobs) {
						ActionLog actionLog = new ActionLog(meter.getSerialNo(), meter.getPhoneNumber(), job.getObis(), 
								job.getType(),job.getEndAt(), job.getStartAt(), job.getId(), job.getExtra());

						//Hourly jobs only run on CT meters
						if(Constants.JobNames.HOURLY.name().equals(job.getName())) {
							if(meter.isCT() || meter.getSerialNo().startsWith("CLE3") == true) {
								actionLog.setStart(job.getCtStartdAt());
								actionLogService.save(actionLog);
							}
						}
						else {
				            actionLogService.save(actionLog);
						}
					}
			
					if(smsSentList.contains(meter.getSerialNo())) {
						log.info("SMS already sent. Skipping:"+meter.getSerialNo() );
						continue;
					}
					
					// Skip SMS if the meter is on scheduled wake up
					if(meter.isScheduleSetup()) {
						continue;
					}
					
					//Don't send SMS to non-CT and Three phase meters for Hourly job
					if((!meter.isCT() && meter.getSerialNo().startsWith("CLE3") == false)
                            && filteredJobs.size() == 1 && filteredJobs.get(0).getName().equals(Constants.JobNames.HOURLY.name())) {
						//log.info("Don't send SMS to non-CT meter for Hourly job:"+meter.getSerialNo() );
						continue;
					}

					SMPPHelper smppHelper = new SMPPHelper("MONITOR", meter.getSerialNo(), meter.getPhoneNumber());
					if (!smppHelper.sendSMSWithRetry()) {
						log.error("MONITOR: Unable to send SMS to: " + meter.getPhoneNumber());
						errorLogService.save(new ErrorLog(meter.getId(), "MONITOR", "Unable to send SMS to "+meter.getPhoneNumber()));
					}
					
	                if(batchCount == batchSize && batchCount < meters.size()) {
	                	batchCount = 1;
	                	try {
	                        Thread.sleep(60*1000);
	                        log.info("Another batch is on");
	                    } catch (InterruptedException ignored) {

	                    }
	                }
	                batchCount++;

				}
			}
		}
	}

	private List<Job> filterJobs(List<Job> readyJobs) {
		if(readyJobs == null) {		
			return null;
		}
		
		List<String> removeList = new ArrayList<String>();

		removeList.add(Constants.JobNames.DEMAND.name()); // Processed separately
		
		for(Job job : readyJobs) {
			if(Constants.JobNames.MONTHLY.name().equals(job.getName())) {
				removeList.add(Constants.JobNames.DAILY.name());
				log.info("Will skip daily if monthly will be running");
//				if(!removeList.contains(Constants.JobNames.HOURLY.name())) {
//					removeList.add(Constants.JobNames.HOURLY.name());
//					log.info("Will skip hourly if monthly will be running");
//				}
			}
//			else if(Constants.JobNames.DAILY.name().equals(job.getName())) {
//				if(!removeList.contains(Constants.JobNames.HOURLY.name())) {
//					removeList.add(Constants.JobNames.HOURLY.name());
//					log.info("Will skip hourly if daily will be running");
//				}
//			}
		}
		List<Job> filteredList = new ArrayList<Job>();
		for(Job job : readyJobs) {
			if(!removeList.contains(job.getName())) {
				job.setStatus(Constants.JobStatus.RUNNING.name());
				filteredList.add(job);
				jobService.save(job);
			}
		}
		
		return filteredList.size() > 0 ? filteredList: null;
	}

	private void processActionLog() {
		log.info("Processing action logs");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) - 2);
		
		Calendar retryStart = Calendar.getInstance();
		retryStart.setTime(new Date());
		retryStart.set(Calendar.HOUR_OF_DAY, 10);
		retryStart.set(Calendar.MINUTE, 0);
		retryStart.set(Calendar.SECOND, 0);
		retryStart.set(Calendar.MILLISECOND, 0);
		Calendar retryEnd = Calendar.getInstance();
		retryEnd.setTime(retryStart.getTime());
		retryEnd.set(Calendar.MINUTE, 10);
		
		Calendar dailyStart = Calendar.getInstance();
		dailyStart.setTime(new Date());
		dailyStart.set(Calendar.HOUR_OF_DAY, 0);
		dailyStart.set(Calendar.MINUTE, 0);
		dailyStart.set(Calendar.SECOND, 0);
		dailyStart.set(Calendar.MILLISECOND, 0);
		Calendar dailyEnd = Calendar.getInstance();
		dailyEnd.setTime(dailyStart.getTime());
		dailyEnd.set(Calendar.HOUR_OF_DAY, 10);
		dailyEnd.set(Calendar.MINUTE, 10);
		Date now = new Date();

		try {

			//1. Fetch new and to be retried actions
			//2. Don't process if already retried 5 times
			//3. If can be retried and past delay time
			//4. Or new and after 2 minutes
			//       on then process them (send SMS and update action)
			List<ActionLog> actionLogs = actionLogService.findAll();
			if(actionLogs != null) {
				for (ActionLog actionLog : actionLogs) {

					try {

						if(actionLog.getCounter() < 5) {


							if(actionLog.getCreatedAt() != null && actionLog.getCreatedAt().before(calendar.getTime())) {

								// Handle prepayment token actions differently: 
								// 	if failed or retried send token directly to the customer
								if(Constants.ActionName.TOKEN_GEN.name().equals(actionLog.getType())
										&& (actionLog.getCounter() > 0 
												|| Constants.ActionStatus.FAILED.name().equals(actionLog.getStatus()))) {

									if(sendToken(actionLog)) {
										actionLogService.deleteById(actionLog.getId());
									}
									else {
										log.error("Unable to send SMS token to: " +  actionLog.getSerialNo());
									}
									continue;
								}

								// Skip SMS if the meter is on scheduled wake up
								Meter meter = meterService.findBySerialNo(actionLog.getSerialNo());

								if(meter != null && meter.isScheduleSetup()) {
									continue;
								}

								SMPPHelper smppHelper = new SMPPHelper("MONITOR", actionLog.getSerialNo(), actionLog.getPhoneNumber());
								if (smppHelper.sendSMSWithRetry()) {
									//increase actions counter
									if (Constants.ActionName.METER_FW_UPDATE.name().equals(actionLog.getType())
											|| Constants.ActionName.MODULE_FW_UPDATE.name().equals(actionLog.getType())) {
										actionLog.setCounter(2);
									} else {
										actionLog.setCounter(1);
									}
									actionLogService.save(actionLog);

									smsSentList.add(actionLog.getSerialNo());
								}
								else {
									log.error("Unable to send SMS for action serail number: "+actionLog.getSerialNo());
									ErrorLog errorLog = new ErrorLog();
									errorLog.setErrorType("monitor");
									//errorLog.setMeterId(meter.getId());
									errorLog.setErrorInfo("Unable to send SMS");
									errorLogService.save(errorLog);
								}
							}

						} else {


							// Auto retry daily actions between 10am and 10:10am daily
							if(Constants.ActionName.DAILY.name().equals(actionLog.getType())
									&& now.after(retryStart.getTime()) && now.before(retryEnd.getTime())
									&& actionLog.getCreatedAt().after(dailyStart.getTime()) && actionLog.getCreatedAt().before(dailyEnd.getTime())
									&& Constants.ActionStatus.AUTO_RETRY.name().equals(actionLog.getStatus()) == false) {
								log.info("Auto retry meter: "+actionLog.getSerialNo());
								actionLog.setStatus(Constants.ActionStatus.AUTO_RETRY.name());
								actionLog.resetCounter();
								actionLogService.save(actionLog);
							}
							else if(Constants.ActionStatus.METER_OFFLINE.name().equals(actionLog.getStatus()) == false){
								Meter meter = meterService.findBySerialNo(actionLog.getSerialNo());
								if(meter != null) {
									if( meter.getOnline()) {
										log.info("Setting meter to offline: "+meter.getSerialNo());
										meter.setOnline(false);
										meterService.save(meter);
										actionLog.setStatus(Constants.ActionStatus.METER_OFFLINE.name());
										actionLogService.save(actionLog);

									}
								}
								else {
									log.error("Unable to find meter from DB for action serail number: "+actionLog.getSerialNo());
								}
							}


						}

					} catch (Exception e) {
						StringWriter sw = new StringWriter();
						e.printStackTrace(new PrintWriter(sw));
						log.error("Exception StackTrace:" + sw.toString());
						e.printStackTrace();
					}
				} // actions
			}

		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			log.error("Exception StackTrace:" + sw.toString());
			e.printStackTrace();
			errorLogService.save(new ErrorLog(-1, "MONITOR", "Unable to process acction logs: "+e.getMessage()));

		}

	}

	private boolean sendToken(ActionLog actionLog) {
		String params[] = actionLog.getObis().split(",");
		String customerPhone = params[0];
		String token = params[1];
		SMPPHelper smppHelper = new SMPPHelper("MONITOR", actionLog.getSerialNo(), customerPhone);

		String message = "PREPAYMENT TOKEN \n"
				+ "Walaaal, token kaan kushubo meter kaaga.\n"
				+ "token: "+token;

		return smppHelper.sendSMSWithRetry(message, Constants.UTILITY_NAME);

	}

}
