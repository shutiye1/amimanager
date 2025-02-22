package safariami.manager.controller;

import safariami.manager.job.JobBase;
import safariami.manager.job.NotificationService;
import safariami.manager.model.ActionLog;
import safariami.manager.model.ErrorLog;
import safariami.manager.model.Meter;
import safariami.manager.model.Notification;
import safariami.manager.model.SmsTest;
import safariami.manager.repo.SmsTestRepository;
import safariami.manager.service.ActionLogService;
import safariami.manager.service.ErrorLogService;
import safariami.manager.service.MeterService;
import safariami.manager.util.Constants;
import safariami.manager.util.SMPPHelper;
import safariami.manager.util.error.CustomResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

//@CrossOrigin
@RestController
public class DemandController extends JobBase {


	private static Logger log = LoggerFactory.getLogger(DemandController.class);

	@Autowired
	private MeterService meterService;

	@Autowired
	private ErrorLogService errorLogService;

	@Autowired
	private NotificationService notificationService;

	@Autowired
	private SmsTestRepository smsTestRepository;

	@Autowired
	private ActionLogService actionLogService;

	public DemandController() {

	}

	@GetMapping(path="/demand/OnDemand/{id}")
	public CustomResponse demand(@PathVariable("id") long id) {

		Meter meter = meterService.findById(id).get();

		if(meter == null) {
			return new CustomResponse("Meter not found", HttpStatus.NOT_FOUND.value());
		}


		try {
			List<ActionLog> demandActions = actionLogService.findActionLogsBySerialNoAndTypeAndCounterLessThan(meter.getSerialNo(), Constants.ActionName.DEMAND.name(), 5);
			if (demandActions != null && demandActions.size() > 0) {
				return new CustomResponse("Action is already pending on meter", HttpStatus.NOT_ACCEPTABLE.value());
			}

			SMPPHelper smppHelper = new SMPPHelper("DEMAND", meter.getSerialNo(), meter.getPhoneNumber());
			if (!smppHelper.sendSMSWithRetry()) {
				log.error("DEMAND: Unable to send SMS to: " + meter.getPhoneNumber());
				errorLogService.save(new ErrorLog(meter.getId(), "DEMAND", "Unable to send SMS"));

				return new CustomResponse("Unable to send SMS to meter", HttpStatus.EXPECTATION_FAILED.value());
			}

			ActionLog actionLog = new ActionLog(meter.getSerialNo(), meter.getPhoneNumber(), "1.0.96.91.10.255", Constants.ActionName.DEMAND.name(),null,null);
			actionLogService.save(actionLog);

			return new CustomResponse("Action queued for the meter", HttpStatus.ACCEPTED.value());
		} catch (Exception e) {
			log.error("DEMAND: Unable to read action log from DB for meter {0}. Error: {1}" + meter.getPhoneNumber(), e.getMessage());
			errorLogService.save(new ErrorLog(meter.getId(), "DEMAND", "Unable to read action log from DB: "+e.getMessage()));
			return new CustomResponse("Unable to read action log from DB: "+e.getMessage(), HttpStatus.EXPECTATION_FAILED.value());
		}

	}

	@GetMapping(path = "/demand/Monthly/{id}")
	public CustomResponse monthly(@PathVariable("id") long id) {

		Meter meter = meterService.findById(id).get();

		if(meter == null) {
			return new CustomResponse("Meter not found", HttpStatus.NOT_FOUND.value());
		}

		log.info("Sending Demand SMS to meter: " + meter.getPhoneNumber());
		log.info("RUNNING MONTHLY DEMAND JOB");


		Date start = new Date();
		Calendar end = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		end.setTime(start);
		end.add(Calendar.MONTH, -5);

		log.info("STARTING DATE:" + start.getTime());
		log.info("ENDING DATE:" + end.getTime());

		//there is action   // and the same action already exists
		//return and do not thing;
		// send sms is ok
		//then add action return success
		// unable to  send sms

		try {
			List<ActionLog> demandActions = actionLogService.findActionLogsBySerialNoAndType(meter.getSerialNo(), Constants.ActionName.MONTHLY.name());
			if (demandActions != null && demandActions.size() > 0) {
				return new CustomResponse("Action is already pending on meter", HttpStatus.NOT_ACCEPTABLE.value());
			}

			SMPPHelper smppHelper = new SMPPHelper("MONTHLY", meter.getSerialNo(), meter.getPhoneNumber());

			if (!smppHelper.sendSMSWithRetry()) {
				log.error("MONTHLY: Unable to send SMS to: " + meter.getPhoneNumber());
				errorLogService.save(new ErrorLog(meter.getId(), "MONTHLY", "Unable to send SMS"));

				return new CustomResponse("Unable to send SMS to meter", HttpStatus.EXPECTATION_FAILED.value());
			}

			ActionLog actionLog = new ActionLog(meter.getSerialNo(), meter.getPhoneNumber(),
					"1.0.98.1.0.255", Constants.ActionName.MONTHLY.name(), end.getTime(), start);
			actionLogService.save(actionLog);

			return new CustomResponse("Action queued for the meter", HttpStatus.ACCEPTED.value());
		} catch (Exception e) {
			log.error("MONTHLY: Unable to read action log from DB for meter {0}. Error: {1}" + meter.getPhoneNumber(), e.getMessage());
			errorLogService.save(new ErrorLog(meter.getId(), "MONTHLY", "Unable to read action log from DB: "+e.getMessage()));
			return new CustomResponse("Unable to read action log from DB: "+e.getMessage(), HttpStatus.EXPECTATION_FAILED.value());
		}

	}

	@GetMapping(path = "/demand/Daily/{id}")
	public CustomResponse daily(@PathVariable("id") long id) {

		Meter meter = meterService.findById(id).get();
		if(meter == null) {
			return new CustomResponse("Meter not found", HttpStatus.NOT_FOUND.value());
		}

		log.info("RUNNING DAILY JOB");

		Date start = new Date();
		Calendar end = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		end.setTime(start);
		end.add(Calendar.DATE, -15);

		log.info("DAILY STARTING DATE:" + start);
		log.info("DAILY ENDING DATE:" + end.getTime());

		try {
			List<ActionLog> demandActions = actionLogService.findActionLogsBySerialNoAndType(meter.getSerialNo(), Constants.ActionName.DAILY.name());
			if (demandActions != null && demandActions.size() > 0) {
				return new CustomResponse("Action is already pending on meter", HttpStatus.NOT_ACCEPTABLE.value());
			}

			SMPPHelper smppHelper = new SMPPHelper("DAILY", meter.getSerialNo(), meter.getPhoneNumber());
			if (!smppHelper.sendSMSWithRetry()) {
				log.error("DAILY: Unable to send SMS to: " + meter.getPhoneNumber());
				errorLogService.save(new ErrorLog(meter.getId(), "DAILY", "Unable to send SMS"));

				return new CustomResponse("Unable to send SMS to meter", HttpStatus.EXPECTATION_FAILED.value());
			}

			ActionLog actionLog = new ActionLog(meter.getSerialNo(), meter.getPhoneNumber(),
					"1.0.98.2.0.255", Constants.ActionName.DAILY.name(), end.getTime(), start);
			actionLogService.save(actionLog);

			return new CustomResponse("Action queued for the meter", HttpStatus.ACCEPTED.value());
		} catch (Exception e) {
			log.error("DAILY: Unable to read action log from DB for meter {0}. Error: {1}" + meter.getPhoneNumber(), e.getMessage());
			errorLogService.save(new ErrorLog(meter.getId(), "DAILY", "Unable to read action log from DB: "+e.getMessage()));
			return new CustomResponse("Unable to read action log from DB: "+e.getMessage(), HttpStatus.EXPECTATION_FAILED.value());
		}

	}

	@GetMapping(path = "/demand/Hourly/{id}")
	public CustomResponse hourly(@PathVariable("id") long id) {
		Meter meter = meterService.findById(id).get();
		if(meter == null) {
			return new CustomResponse("Meter not found", HttpStatus.NOT_FOUND.value());
		}

		log.info("RUNNING DEMAND HOURLY SERIAL: " + meter.getSerialNo() +" ID: "+id);
		getRetryActions(meter);
		return new CustomResponse("Action queued for the meter", HttpStatus.ACCEPTED.value());
	}

	@GetMapping(path = "/demand/SendSMS/{id}")
	public CustomResponse sendSMS(@PathVariable("id") int id) {

		Optional<SmsTest> oMeter = smsTestRepository.findById((long) id);
		if(oMeter == null || !oMeter.isPresent()) {
			return new CustomResponse("Meter not found", HttpStatus.NOT_FOUND.value());
		}

		SmsTest meter = oMeter.get();

		SMPPHelper smppHelper = new SMPPHelper("TEST", meter.getName(), meter.getPhoneNumber());
		if (smppHelper.sendSMSWithRetry()) {
			return new CustomResponse("Action queued for the meter", HttpStatus.ACCEPTED.value());
		} else {
			log.error("SMSTEST: Unable to send SMS to: " + meter.getPhoneNumber());

			errorLogService.save(new ErrorLog(-1, "SMSTEST", "Unable to send SMS"));

			return new CustomResponse("Unable to send SMS to meter", HttpStatus.EXPECTATION_FAILED.value());
		}

	}


	@GetMapping(path = "/demand/config/{id}")
	public CustomResponse changeConfig(@PathVariable("id") long id) {

		Meter meter = meterService.findById(id).get();
		if(meter == null) {
			return new CustomResponse("Meter not found", HttpStatus.NOT_FOUND.value());
		}

		log.info("RUNNING HOURLY  JOB");
		Date start = new Date();
		Calendar end = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		end.setTime(start);
		end.add(Calendar.HOUR, -12);

		try {
			List<ActionLog> demandActions = actionLogService.findActionLogsBySerialNoAndType(meter.getSerialNo(), Constants.ActionName.DATA_CONFIG.name());
			if (demandActions != null && demandActions.size() > 0) {
				return new CustomResponse("Action is already pending on meter", HttpStatus.NOT_ACCEPTABLE.value());
			}

			SMPPHelper smppHelper = new SMPPHelper("CONFIG", meter.getSerialNo(), meter.getPhoneNumber());
			if (!smppHelper.sendSMSWithRetry()) {
				log.error("CONFIG: Unable to send SMS to: " + meter.getPhoneNumber());
				errorLogService.save(new ErrorLog(meter.getId(), "DATA:CONFIG", "Unable to send SMS"));

				return new CustomResponse("Unable to send SMS to meter", HttpStatus.EXPECTATION_FAILED.value());
			}

			ActionLog actionLog = new ActionLog(meter.getSerialNo(), meter.getPhoneNumber(),
					"1.0.99.1.0.255", Constants.ActionName.DATA_CONFIG.name(), end.getTime(), start);
			actionLogService.save(actionLog);

			return new CustomResponse("Action queued for the meter", HttpStatus.ACCEPTED.value());
		} catch (Exception e) {
			log.error("DATA:CONFIG: Unable to read action log from DB for meter {0}. Error: {1}" + meter.getPhoneNumber(), e.getMessage());
			errorLogService.save(new ErrorLog(meter.getId(), "DATA:CONFIG", "Unable to read action log from DB: "+e.getMessage()));
			return new CustomResponse("Unable to read action log from DB: "+e.getMessage(), HttpStatus.EXPECTATION_FAILED.value());
		}


	}

	@PostMapping(path="/notification/send")
	public CustomResponse sendNotification(@RequestBody Notification notification) {
		notificationService.doNotify(notification);

		return new CustomResponse("Event sent successfully", HttpStatus.ACCEPTED.value());
	}

	@GetMapping(path="/notification/receive")
	public SseEmitter receiveNotification() {
		final SseEmitter emitter = new SseEmitter(86400000L);
		notificationService.addEmitter(emitter);
		emitter.onCompletion(() -> notificationService.removeEmitter(emitter));
		emitter.onTimeout(() -> notificationService.removeEmitter(emitter));
		return emitter;
	}


	private void getRetryActions(Meter meter) {
		try {
			List<ActionLog> actionLogs = actionLogService.findActionLogsByRetryAndSerialNo(true, meter.getSerialNo());
			if (actionLogs != null) {
				log.info("Retry ActionLog Size : " + actionLogs.size() + ", Serial: " + meter.getSerialNo());

				SMPPHelper smppHelper = new SMPPHelper("HOURLY", meter.getSerialNo(), meter.getPhoneNumber());
				if (!smppHelper.sendSMSWithRetry()) {
					log.error("HOURLY: Unable to send SMS to: " + meter.getPhoneNumber());
					errorLogService.save(new ErrorLog(meter.getId(), "HOURLY", "Unable to send SMS"));
				}
			}

		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			log.error("Exception StackTrace:" + sw.toString());
			e.printStackTrace();
		}
	}

}