package safariami.manager.job;

import safariami.manager.model.Job;
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

@Component
public class MonthlyJob extends JobBase {

    @Autowired
    private JobService jobService;

    private static Logger log = LoggerFactory.getLogger(MonthlyJob.class);

    
    public MonthlyJob() {
    }

    @Scheduled(cron = "0 10 0 25 * *") // the correct line
    public void run() {
        try {
            log.info("RUNNING MONTHLY JOB");
            Calendar start = Calendar.getInstance();
            start.setTime(new Date());
            start.set(Calendar.HOUR_OF_DAY, 0);
            start.set(Calendar.MINUTE, 0);
            start.set(Calendar.SECOND, 0);
            start.set(Calendar.MILLISECOND, 0);
            //start.set(Calendar.DAY_OF_MONTH, 1);// first of the month
            start.set(Calendar.DAY_OF_MONTH, 24); // the correct line


            Calendar end = Calendar.getInstance();
            end.setTime(start.getTime());
            end.set(Calendar.DAY_OF_MONTH, 28); // the correct line

            log.info("Monthly Range: "+start.getTime() +"   "+end.getTime());

            Job monthlyJob = new Job();
            monthlyJob.setName(Constants.JobNames.MONTHLY.name());
            monthlyJob.setType(Constants.ActionName.MONTHLY.name());
            monthlyJob.setPriority(Constants.JobPriorty.MONTHLY.getValue());
            monthlyJob.setStatus(Constants.JobStatus.READY_TO_RUN.name());
            monthlyJob.setStartAt(start.getTime());
            monthlyJob.setEndAt(end.getTime());
            monthlyJob.setObis("1.0.98.1.0.255");
            jobService.save(monthlyJob);

        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            log.error("Exception StackTrace:" + sw.toString());
        }

    }
}
