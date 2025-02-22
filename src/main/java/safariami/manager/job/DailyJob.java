package safariami.manager.job;

import safariami.manager.model.ErrorLog;
import safariami.manager.model.Job;
import safariami.manager.service.ErrorLogService;
import safariami.manager.service.JobService;
import safariami.manager.util.Constants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class DailyJob extends JobBase {

	@Autowired
	private JobService jobService;
	@Autowired
	private ErrorLogService errorLogService;

	private static Logger log = LoggerFactory.getLogger(DailyJob.class);


	public DailyJob() {
	}


	@Scheduled(cron = "0 0 3 * * *") // the correct line
	public void run() {
		try {
			log.info("RUNNING DAILY  JOB");
			Calendar start = Calendar.getInstance();
			start.setTime(new Date());
			start.set(Calendar.HOUR_OF_DAY, 0);
			start.set(Calendar.MINUTE, 0);
			start.set(Calendar.SECOND, 0);
			start.set(Calendar.MILLISECOND, 0);
			Calendar end = Calendar.getInstance();
			end.setTime(start.getTime());
			end.set(Calendar.MINUTE, 15);

			//Don't run if MONTHLY is running
			if(monthlyRunning()) {
				log.info("Skipping DAILY since MONTHLY is running");
				return;
			}

			Job dailyJob = new Job();
			dailyJob.setName(Constants.JobNames.DAILY.name());
			dailyJob.setType(Constants.ActionName.DAILY.name());
			dailyJob.setPriority(Constants.JobPriorty.DAILY.getValue());
			dailyJob.setStatus(Constants.JobStatus.READY_TO_RUN.name());
			dailyJob.setStartAt(start.getTime());
			dailyJob.setEndAt(end.getTime());
			dailyJob.setObis("1.0.98.2.0.255");
			jobService.save(dailyJob);


		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			log.error("Exception StackTrace:" + sw.toString());
			errorLogService.save(new ErrorLog(-1, "DAILY", "Unable to save DAILY job to DB: "+e.getMessage()));

		}

	}


	private boolean monthlyRunning() {

		List<Job> monthlyJobs = jobService.findJobByName(Constants.JobNames.MONTHLY.name());

		if(monthlyJobs == null) {
			return false;
		}

		for(Job job : monthlyJobs) {
			if(Constants.JobStatus.READY_TO_RUN.name().equals(job.getStatus())) {
				return true;

			}
		}

		return false;
	}
}
