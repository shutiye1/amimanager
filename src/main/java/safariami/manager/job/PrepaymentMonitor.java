package safariami.manager.job;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import safariami.manager.model.Account;
import safariami.manager.model.ActionLog;
import safariami.manager.model.Prepayment;
import safariami.manager.model.PrepaymentLog;
import safariami.manager.model.PrepaymentOverdraft;
import safariami.manager.model.PrepaymentStatus;
import safariami.manager.service.AccountService;
import safariami.manager.service.ActionLogService;
import safariami.manager.service.PrepaymentLogService;
import safariami.manager.service.PrepaymentOverdraftService;
import safariami.manager.service.PrepaymentService;
import safariami.manager.service.PrepaymentStatusService;
import safariami.manager.util.Constants;
import safariami.manager.util.SMPPHelper;

@Slf4j
@Component
public class PrepaymentMonitor extends JobBase {

	@Autowired
	PrepaymentLogService prepaymentLogService;

	@Autowired
	PrepaymentStatusService prepaymentStatusService;

	@Autowired
	PrepaymentService prepaymentService;

	@Autowired
	AccountService accountService;

	@Autowired
	private ActionLogService actionLogService;

	@Autowired
	PrepaymentOverdraftService prepaymentOverdraftService;

	//@Scheduled(fixedDelay = (60 * 10) * 1000) // the correct line
	public void run() {
		log.info("RUNNING PREPAYMENT MONITOR JOBS: "+Constants.UTILITY_NAME);

		List<PrepaymentOverdraft> disconnectedOverdrafts = prepaymentOverdraftService.findByStatus(Constants.OverdraftStatus.DISCONNECTED.name());

		if(disconnectedOverdrafts != null) {
			processDisconnectedOverdrafts(disconnectedOverdrafts);
		}

		List<PrepaymentLog> prepaymentLogs = prepaymentLogService.findAll();
		if(prepaymentLogs != null) {
			processPrepaymentLog(prepaymentLogs);
		}
		else {
			log.info("Prepayment log size: 0");
		}

	}

	private void processDisconnectedOverdrafts(List<PrepaymentOverdraft> disconnectedOverdrafts) {
		for(PrepaymentOverdraft overdraft : disconnectedOverdrafts) {
			Prepayment prepayment = prepaymentService.findByCustomerId(overdraft.getCustomerId());

			if(prepayment == null) {
				log.error("Unable to find prepayment for customer Id:"+overdraft.getCustomerId());
				continue;
			}

			PrepaymentStatus prepaymentStatus = prepaymentStatusService.findBySerialNo(prepayment.getSerialNo());
			if(prepaymentStatus == null) {
				prepaymentStatus = prepaymentStatusService.save(new PrepaymentStatus(prepayment.getSerialNo(), 
						prepayment.getMeterPhone(), new BigDecimal("0.00"), Constants.OverdraftStatus.CONNECTED.name(), 
						new BigDecimal("0.00")));
			}

			Account account = accountService.findByAccountId(overdraft.getCustomerId());

			if(account == null) {
				log.error("Unable to find aaccount Id:"+overdraft.getCustomerId());
				continue;
			}

			if(account.getBalance().compareTo(prepayment.getOverdraftThreshold()) > 0) {
				processReconnect(overdraft, prepayment);

				//prepaymentStatus.setStatus(Constants.OverdraftStatus.CONNECTED.name());
				prepaymentStatusService.save(prepaymentStatus);
			}

		}

	}

	//@Async
	private void processPrepaymentLog(List<PrepaymentLog> prepaymentLogs) {
		// 1. Calculate usage
		// 2. Send warning SMS if close to TH
		// 3. Disconnect if below or at TH
		// 4. Skip if disconnected
		for(PrepaymentLog prepaymentLog : prepaymentLogs) {
			Prepayment prepayment = prepaymentService.findBySerialNo(prepaymentLog.getSerialNo());
			
			if(prepayment == null) {
				log.error("Unable to find prepayment for serial no:"+prepaymentLog.getSerialNo());
				continue;
			}
			
			int maxWarning = prepayment.getMaxNotification();
			if(maxWarning <= 0) {
				maxWarning = Constants.MAX_NOTIFICATION;
			}

			// Adjust total usage based on initial reading
			BigDecimal totalUsage = subtractBigDecimal(prepayment.getInitialReading(), prepaymentLog.getTotalActiveImport());

			// Check if usage is 0
			if(totalUsage.compareTo(BigDecimal.ZERO) <= 0) {
				log.info("Usage is 0 for customer ID: "+prepayment.getCustomerId());
				prepaymentLogService.deleteById(prepaymentLog.getId());
				continue;
			}

			PrepaymentStatus prepaymentStatus = prepaymentStatusService.findBySerialNo(prepaymentLog.getSerialNo());
			if(prepaymentStatus == null) {
				prepaymentStatus = prepaymentStatusService.save(new PrepaymentStatus(prepaymentLog.getSerialNo(), 
						prepaymentLog.getPhoneNumber(), new BigDecimal("0.00"), Constants.OverdraftStatus.CONNECTED.name(), 
						prepaymentLog.getTotalActiveImport()));
			}

			// Adjust prev reading based on initial reading
			BigDecimal prevReading = subtractBigDecimal(prepayment.getInitialReading(), prepaymentStatus.getPrevReading());
			
			// Reduce total usage further by prev usage 
			totalUsage = subtractBigDecimal(prevReading, totalUsage);

			//log.info("Total usage: ", totalUsage.doubleValue());
			
			if(totalUsage.compareTo(BigDecimal.ZERO) > 0) {
				
				BigDecimal usedAmount = totalUsage.multiply(new BigDecimal(prepayment.getRatePlan())).setScale(3, RoundingMode.HALF_UP);
				
				//log.info("Used amount: ", usedAmount.doubleValue());
				
				try {
					Account account = accountService.withraw(usedAmount, prepayment.getCustomerId());

					// Check for overdraft
					PrepaymentOverdraft ppOverdraft = prepaymentOverdraftService.findByCustomerId(prepayment.getCustomerId());

					if((ppOverdraft == null || ppOverdraft.getWarningCount() < maxWarning)
							&& account.getBalance().compareTo(prepayment.getOverdraftThreshold()) <= 0) {
						sendWarning(ppOverdraft, account, prepayment);
						//prepaymentStatus.setStatus(Constants.OverdraftStatus.CONNECTED.name());

					}
					else if(account.getBalance().compareTo(prepayment.getOverdraftThreshold()) <= 0
							&& ppOverdraft.getWarningCount() == maxWarning 
							&& prepaymentStatus.getStatus().equals(Constants.OverdraftStatus.CONNECTED.name())) {
						processDisconnection(ppOverdraft, account, prepayment);
						//prepaymentStatus.setStatus(Constants.OverdraftStatus.DISCONNECTED.name());
					}


				} catch (Exception e) {
					//e.printStackTrace();
					log.error("Unable to process prepayment: "+ prepayment.getCustomerId()+". Error: "+e.getMessage());
				}
			}


			prepaymentLogService.deleteById(prepaymentLog.getId());

			prepaymentStatus.setPrevReading(prepaymentStatus.getCurrentReading());
			prepaymentStatus.setCurrentReading(prepaymentLog.getTotalActiveImport());
			prepaymentStatusService.save(prepaymentStatus);

		}
	}

	private void processReconnect(PrepaymentOverdraft ppOverdraft, Prepayment prepayment) {
		log.info("Reconnecting:"+prepayment.getCustomerId());

		//1. Create DISCONNECT action log
		ActionLog actionLog = new ActionLog(prepayment.getSerialNo(), prepayment.getMeterPhone(), 
				"1.0.96.91.10.255", Constants.ActionName.CONNECT.name(),null,null);
		actionLogService.save(actionLog);

		//2. Send SMS to the meter
		SMPPHelper smppHelper = new SMPPHelper("PREPAYMENT MONITOR", prepayment.getSerialNo(), prepayment.getMeterPhone());
		if (!smppHelper.sendSMSWithRetry()) {
			log.error("PREPAYMENT: Unable to send SMS to: " +  prepayment.getMeterPhone());
		}

		//3. remove overdraft
		prepaymentOverdraftService.deleteById(ppOverdraft.getId());
	}

	private void sendWarning(PrepaymentOverdraft ppOverdraft, Account account, Prepayment prepayment) {
		log.info("Sending overdraft warning to:"+prepayment.getCustomerId());

		//3. Record overdraft
		if(ppOverdraft == null) {
			ppOverdraft = prepaymentOverdraftService.save(new PrepaymentOverdraft(prepayment.getCustomerId(), account.getBalance(), 
					Constants.OverdraftStatus.CONNECTED.name(), 1));
		}
		else {
			ppOverdraft.setOverdraftAmount(account.getBalance());
			//ppOverdraft.setStatus(Constants.OverdraftStatus.CONNECTED.name());
			ppOverdraft.setWarningCount(ppOverdraft.getWarningCount()+1);
			ppOverdraft = prepaymentOverdraftService.save(ppOverdraft);
		}

		//3. Notify customer of disconnection
		SMPPHelper smppHelper = new SMPPHelper("PREPAYMENT MONITOR", prepayment.getSerialNo(), prepayment.getCustomerPhone());

		String message = "OGAYSIIS #"+ppOverdraft.getWarningCount()+"\n"
				+ "Walaaal, lacagtii korontadu way kaa dhamaatay.\n"
				+ "Kushubo:*"+Constants.TOPUP_SHORCODE+"*"+Constants.UTILITY_ACCOUNT+"*Lacagta#\n"
				+ "Haraagaagu waa: "+account.getBalance();

		if (!smppHelper.sendSMSWithRetry(message, Constants.UTILITY_NAME)) {
			log.error("PREPAYMENT MONITOR: Unable to send SMS to: " +  prepayment.getCustomerPhone());
		}		
	}

	private void processDisconnection(PrepaymentOverdraft ppOverdraft, Account account, Prepayment prepayment) {

		log.info("Disconnecting:"+prepayment.getCustomerId());

		//1. Create DISCONNECT action log
		ActionLog actionLog = new ActionLog(prepayment.getSerialNo(), prepayment.getMeterPhone(), 
				"1.0.96.91.10.255", Constants.ActionName.DISCONNECT.name(),null,null);
		actionLogService.save(actionLog);

		//2. Send SMS to the meter
		SMPPHelper smppHelper = new SMPPHelper("PREPAYMENT MONITOR", prepayment.getSerialNo(), prepayment.getMeterPhone());
		if (!smppHelper.sendSMSWithRetry()) {
			log.error("PREPAYMENT: Unable to send SMS to: " +  prepayment.getMeterPhone());
		}

		//3. Record overdraft
		if(ppOverdraft == null) {
			prepaymentOverdraftService.save(new PrepaymentOverdraft(prepayment.getCustomerId(), account.getBalance(), 
					Constants.OverdraftStatus.CONNECTED.name(), 0));
		}
		else {
			ppOverdraft.setOverdraftAmount(account.getBalance());
			//ppOverdraft.setStatus(Constants.OverdraftStatus.DISCONNECTED.name());
			ppOverdraft.setWarningCount(ppOverdraft.getWarningCount()+1);
			prepaymentOverdraftService.save(ppOverdraft);
		}

		//3. Notify customer of disconnection
		smppHelper = new SMPPHelper("PREPAYMENT MONITOR", prepayment.getSerialNo(), prepayment.getCustomerPhone());

		String message = "OGAYSIIS KII UGU DANBEEYAY!\n"
				+ "Walaaal, lacagtii korontadu way kaa dhamaatay.\n"
				+ "Kushubo:*"+Constants.TOPUP_SHORCODE+"*"+Constants.UTILITY_ACCOUNT+"*Lacagta#\n"
				+ "Haraagaagu waa: "+account.getBalance();

		if (!smppHelper.sendSMSWithRetry(message, Constants.UTILITY_NAME)) {
			log.error("PREPAYMENT MONITOR: Unable to send SMS to: " +  prepayment.getCustomerPhone());
		}
	}
	
	private BigDecimal subtractBigDecimal(BigDecimal v1, BigDecimal v2) {
		if(v1.compareTo(v2) < 0) {
			return v2.subtract(v1);
		}
		else {
			return v1.subtract(v2);
		}
	}
}
