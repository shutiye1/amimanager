package safariami.manager.job;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import safariami.manager.model.Job;
import safariami.manager.service.JobService;
import safariami.manager.util.Constants;

@Component
public class PrepaymentJob extends JobBase {
	
    @Autowired
    private JobService jobService;

    private static Logger log = LoggerFactory.getLogger(PrepaymentJob.class);

    public PrepaymentJob() {
    }


    //@Scheduled(fixedDelay = (1 * 3600) * 1000) // the correct line
    public void run() {
        try {
            log.info("RUNNING PREPAYMENT JOB");
            Calendar startDate = Calendar.getInstance();
            startDate.set(Calendar.MINUTE, 0);
            startDate.set(Calendar.SECOND, 0);
            startDate.set(Calendar.MILLISECOND, 0);
            Date endDate = startDate.getTime();
            startDate.add(Calendar.HOUR, -1);
            Calendar ctStartDate =  (Calendar) startDate.clone();
            ctStartDate.add(Calendar.HOUR, -1);

            Job hourlyJob = new Job();
            hourlyJob.setName(Constants.JobNames.DEMAND.name());
            hourlyJob.setType(Constants.ActionName.DEMAND.name());
            hourlyJob.setPriority(Constants.JobPriorty.DEMAND.getValue());
            hourlyJob.setStatus(Constants.JobStatus.READY_TO_RUN.name());
            hourlyJob.setStartAt(startDate.getTime());
            hourlyJob.setEndAt(endDate);
            hourlyJob.setCtStartdAt(ctStartDate.getTime());
            hourlyJob.setObis("1.0.96.91.10.255");
            jobService.save(hourlyJob);


        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            log.error("Exception StackTrace:" + sw.toString());
        }
    }


}
