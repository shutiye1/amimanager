package safariami.manager.job;

import safariami.manager.model.Job;
import safariami.manager.model.LastHourly;
import safariami.manager.repo.LastHourlyRepository;
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
public class HourlyJob extends JobBase {

	@Autowired
    private LastHourlyRepository lastHourlyRepository;
    @Autowired
    private JobService jobService;

    private static Logger log = LoggerFactory.getLogger(HourlyJob.class);

    public HourlyJob() {
    }


    @Scheduled(fixedDelay = (1 * 3600) * 1000) // the correct line
    public void run() {
        try {
            log.info("RUNNING HOURLY JOB");
            Calendar startDate = Calendar.getInstance();
            startDate.set(Calendar.MINUTE, 0);
            startDate.set(Calendar.SECOND, 0);
            startDate.set(Calendar.MILLISECOND, 0);
            Date endDate = startDate.getTime();
            startDate.add(Calendar.MINUTE, -50);
            //Calendar ctStartDate =  (Calendar) startDate.clone();
            //ctStartDate.add(Calendar.HOUR, -5);

            LastHourly lastHourly = new LastHourly();
            lastHourly.setId(1);
            lastHourly.setStart(startDate.getTime());
            lastHourly.setEnd(endDate);
            lastHourlyRepository.save(lastHourly);
            
            // Don't run if monthly or daily is running
//            if(monthlyOrDailyRunning()) {
//            	log.info("Skipping HOURLY since DAILY or MONTHLY is running");
//            	return;
//            }

            Job hourlyJob = new Job();
            hourlyJob.setName(Constants.JobNames.HOURLY.name());
            hourlyJob.setType(Constants.ActionName.HOURLY.name());
            hourlyJob.setPriority(Constants.JobPriorty.HOURLY.getValue());
            hourlyJob.setStatus(Constants.JobStatus.READY_TO_RUN.name());
            hourlyJob.setStartAt(startDate.getTime());
            hourlyJob.setEndAt(endDate);
            hourlyJob.setCtStartdAt(startDate.getTime());
            hourlyJob.setObis("1.0.99.1.0.255");
            jobService.save(hourlyJob);


        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            log.error("Exception StackTrace:" + sw.toString());
        }
    }


	public boolean monthlyOrDailyRunning() {
		
		List<Job> monthlyJobs = jobService.findJobByName(Constants.JobNames.MONTHLY.name());
    	if(monthlyJobs != null) {
    		for(Job job : monthlyJobs) {
    			if(Constants.JobStatus.READY_TO_RUN.name().equals(job.getStatus())) {
    				return true;
    			}
    		}
    	}

		List<Job> dailyJobs = jobService.findJobByName(Constants.JobNames.DAILY.name());
    	if(dailyJobs != null ) {
    		for(Job job : dailyJobs) {
    			if(Constants.JobStatus.READY_TO_RUN.name().equals(job.getStatus())) {
    				return true;
    			}
    		}
    	}
		
		return false;
	}
}
