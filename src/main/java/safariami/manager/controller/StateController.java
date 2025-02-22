package safariami.manager.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import safariami.manager.model.ActionLog;
import safariami.manager.model.Config;
import safariami.manager.model.ErrorLog;
import safariami.manager.model.Firmware;
import safariami.manager.model.Job;
import safariami.manager.model.Meter;
import safariami.manager.model.PrepaymentStatus;
import safariami.manager.model.Request;
import safariami.manager.model.Watch;
import safariami.manager.service.ActionLogService;
import safariami.manager.service.ErrorLogService;
import safariami.manager.service.FirmwareService;
import safariami.manager.service.JobService;
import safariami.manager.service.MeterService;
import safariami.manager.service.PrepaymentStatusService;
import safariami.manager.util.Constants;
import safariami.manager.util.SMPPHelper;
import safariami.manager.util.error.CustomResponse;
import java.util.List;

@RestController
public class StateController {

	private static Logger log = LoggerFactory.getLogger(StateController.class);

	@Autowired
	private MeterService meterService;

	@Autowired
	private ErrorLogService errorLogService;

	@Autowired
	private ActionLogService actionLogService;

	@Autowired
	private FirmwareService firmwareService;

	@Autowired
	private JobService jobService;
	
	@Autowired
	PrepaymentStatusService prepaymentStatusService;


	@GetMapping(path = "/state/disconnect/{id}")
	public  CustomResponse disconnect(@PathVariable("id") long id) {

		Meter meter = meterService.findById(id).get() ;

		if(meter == null) {
			return new CustomResponse("Meter not found", HttpStatus.NOT_FOUND.value());
		}

		try {
			List<ActionLog> stateActions = actionLogService.findActionLogsBySerialNoAndType(meter.getSerialNo(), Constants.ActionName.DISCONNECT.name());
			if (stateActions != null && stateActions.size() > 0) {
				return new CustomResponse("Action is already pending on meter", HttpStatus.NOT_ACCEPTABLE.value());
			}

			SMPPHelper smppHelper = new SMPPHelper("DISCONNECT", meter.getSerialNo(), meter.getPhoneNumber());
			if (!smppHelper.sendSMSWithRetry()) {
				log.error("DISCONNECT: Unable to send SMS to: " + meter.getPhoneNumber());
				errorLogService.save(new ErrorLog(meter.getId(), "DISCONNECT", "Unable to send SMS"));

				return new CustomResponse("Unable to send SMS to meter", HttpStatus.EXPECTATION_FAILED.value());
			}

			ActionLog actionLog = new ActionLog(meter.getSerialNo(), meter.getPhoneNumber(), "1.0.96.91.10.255", Constants.ActionName.DISCONNECT.name(),null,null);
			actionLogService.save(actionLog);

			return new CustomResponse("Action queued for the meter", HttpStatus.ACCEPTED.value());
		} catch (Exception e) {
			log.error("DISCONNECT: Unable to read action log from DB for meter {0}. Error: {1}" + meter.getPhoneNumber(), e.getMessage());
			errorLogService.save(new ErrorLog(meter.getId(), "DISCONNECT", "Unable to read action log from DB: "+e.getMessage()));
			return new CustomResponse("Unable to read action log from DB: "+e.getMessage(), HttpStatus.EXPECTATION_FAILED.value());
		}

	}

	@GetMapping(path = "/state/connect/{id}")
	public CustomResponse connect(@PathVariable("id") long id)  {

		Meter meter = meterService.findById(id).get();

		if(meter == null) {
			return new CustomResponse("Meter not found", HttpStatus.NOT_FOUND.value());
		}

		try {
			List<ActionLog> stateActions = actionLogService.findActionLogsBySerialNoAndType(meter.getSerialNo(), Constants.ActionName.CONNECT.name());
			if (stateActions != null && stateActions.size() > 0) {
				return new CustomResponse("Action is already pending on meter", HttpStatus.NOT_ACCEPTABLE.value());
			}
			
			// Connect is not allowed for disconnected prepayment meters
			if(meter.isPrepayment()) {
				PrepaymentStatus prepaymentStatus = prepaymentStatusService.findBySerialNo(meter.getSerialNo());
				if(prepaymentStatus != null && prepaymentStatus.getStatus().equals(Constants.OverdraftStatus.DISCONNECTED.name())) {
					return new CustomResponse("Meter is disconnected for low balance. Please top up the account.", HttpStatus.NOT_ACCEPTABLE.value());
				}
			}

			SMPPHelper smppHelper = new SMPPHelper("CONNECT", meter.getSerialNo(), meter.getPhoneNumber());
			if (!smppHelper.sendSMSWithRetry()) {
				log.error("CONNECT: Unable to send SMS to: " + meter.getPhoneNumber());
				errorLogService.save(new ErrorLog(meter.getId(), "CONNECT", "Unable to send SMS"));
				return new CustomResponse("Unable to send SMS to meter", HttpStatus.EXPECTATION_FAILED.value());
			}

			ActionLog actionLog = new ActionLog(meter.getSerialNo(), meter.getPhoneNumber(), "1.0.96.91.10.255", Constants.ActionName.CONNECT.name(),null,null);
			actionLogService.save(actionLog);

			return new CustomResponse("Action queued for the meter", HttpStatus.ACCEPTED.value());
		} catch (Exception e) {
			log.error("CONNECT: Unable to read action log from DB for meter {0}. Error: {1}" + meter.getPhoneNumber(), e.getMessage());
			errorLogService.save(new ErrorLog(meter.getId(), "CONNECT", "Unable to read action log from DB: "+e.getMessage()));
			return new CustomResponse("Unable to read action log from DB: "+e.getMessage(), HttpStatus.EXPECTATION_FAILED.value());
		}

	}


	@PostMapping(path = "/state/apn")
	public CustomResponse apn(@RequestBody Request<Watch> watchRequest) {

		if(watchRequest.getBody() == null || watchRequest.getBody().getSerials() == null){
			return new CustomResponse("No meters To update!", HttpStatus.NOT_FOUND.value());
		}

		for (String serial:watchRequest.getBody().getSerials()) {
			Meter meter = meterService.findBySerialNo(serial);
			if(meter!=null){


				try {
					List<ActionLog> stateActions = actionLogService.findActionLogsBySerialNoAndType(meter.getSerialNo(), Constants.ActionName.APN.name());

					if (stateActions != null && stateActions.size() > 0) {
						return new CustomResponse("Action is already pending on meter", HttpStatus.NOT_ACCEPTABLE.value());
					}

					SMPPHelper smppHelper = new SMPPHelper("APN", meter.getSerialNo(), meter.getPhoneNumber());
					if (!smppHelper.sendSMSWithRetry()) {
						log.error("CONNECT: Unable to send SMS to: " + meter.getPhoneNumber());
						errorLogService.save(new ErrorLog(meter.getId(), "APN", "Unable to send SMS state connect"));
						return new CustomResponse("Unable to send SMS to meter", HttpStatus.EXPECTATION_FAILED.value());
					}

					ActionLog actionLog = new ActionLog(meter.getSerialNo(), meter.getPhoneNumber(), "APN", Constants.ActionName.APN.name(),null,null);
					actionLogService.save(actionLog);
				} catch (Exception e) {
					log.error("APN: Unable to read action log from DB for meter {0}. Error: {1}" + meter.getPhoneNumber(), e.getMessage());
					errorLogService.save(new ErrorLog(meter.getId(), "APN", "Unable to read action log from DB: "+e.getMessage()));
				}

			}else {
				log.info("Invalid serial : " + serial);
			}
		}

		return new CustomResponse("Action queued for the meter", HttpStatus.ACCEPTED.value());
	}

	@GetMapping(path = "/state/tamper/{id}")
	public CustomResponse RemoveTamper(@PathVariable("id") long id) {

		Meter meter = meterService.findById(id).get();

		if(meter == null) {
			return new CustomResponse("Meter not found", HttpStatus.NOT_FOUND.value());
		}

		try {
			List<ActionLog> stateActions = actionLogService.findActionLogsBySerialNoAndType(meter.getSerialNo(), Constants.ActionName.TAMPER.name());
			if (stateActions != null && stateActions.size() > 0) {
				return new CustomResponse("Action is already pending on meter", HttpStatus.NOT_ACCEPTABLE.value());
			}

			SMPPHelper smppHelper = new SMPPHelper("TAMPER", meter.getSerialNo(), meter.getPhoneNumber());
			if (!smppHelper.sendSMSWithRetry()) {
				log.error("TAMPER: Unable to send SMS to: " + meter.getPhoneNumber());
				errorLogService.save(new ErrorLog(meter.getId(), "TAMPER", "Unable to send SMS"));
				return new CustomResponse("Unable to send SMS to meter", HttpStatus.EXPECTATION_FAILED.value());
			}

			ActionLog actionLog = new ActionLog(meter.getSerialNo(), meter.getPhoneNumber(), "0.0.10.0.0.255", Constants.ActionName.TAMPER.name(),null,null);
			actionLogService.save(actionLog);

			return new CustomResponse("Action queued for the meter", HttpStatus.ACCEPTED.value());
		} catch (Exception e) {
			log.error("TAMPER: Unable to read action log from DB for meter {0}. Error: {1}" + meter.getPhoneNumber(), e.getMessage());
			errorLogService.save(new ErrorLog(meter.getId(), "TAMPER", "Unable to read action log from DB: "+e.getMessage()));
			return new CustomResponse("Unable to read action log from DB: "+e.getMessage(), HttpStatus.EXPECTATION_FAILED.value());
		}

	}

	@PostMapping(path = "/state/config")
	public CustomResponse updateConfig( @RequestBody Request<Config> config) {
		Meter meter = meterService.findById((long) config.getBody().getId()).get();

		if(meter == null) {
			return new CustomResponse("Meter not found", HttpStatus.NOT_FOUND.value());
		}

		log.info("CONFIG: Destination : "+config.getBody().getDestination());

		try {
			List<ActionLog> stateActions = actionLogService.findActionLogsBySerialNoAndType(meter.getSerialNo(), Constants.ActionName.STATE_CONFIG.name());
			if (stateActions != null && stateActions.size() > 0) {
				return new CustomResponse("Action is already pending on meter", HttpStatus.NOT_ACCEPTABLE.value());
			}

			SMPPHelper smppHelper = new SMPPHelper("CONFIG", meter.getSerialNo(), meter.getPhoneNumber());
			if (!smppHelper.sendSMSWithRetry()) {
				log.error("STATE:CONFIG: Unable to send SMS to: " + meter.getPhoneNumber());
				errorLogService.save(new ErrorLog(meter.getId(), "STATE:CONFIG", "Unable to send SMS"));
				return new CustomResponse("Unable to send SMS to meter", HttpStatus.EXPECTATION_FAILED.value());
			}

			ActionLog actionLog = new ActionLog(meter.getSerialNo(), meter.getPhoneNumber(), config.getBody().getDestination(), Constants.ActionName.STATE_CONFIG.name(),null,null);
			actionLogService.save(actionLog);

			return new CustomResponse("Action queued for the meter", HttpStatus.ACCEPTED.value());
		} catch (Exception e) {
			log.error("STATE:CONFIG: Unable to read action log from DB for meter {0}. Error: {1}" + meter.getPhoneNumber(), e.getMessage());
			errorLogService.save(new ErrorLog(meter.getId(), "STATE:CONFIG", "Unable to read action log from DB: "+e.getMessage()));
			return new CustomResponse("Unable to read action log from DB: "+e.getMessage(), HttpStatus.EXPECTATION_FAILED.value());
		}

	}

	@PostMapping(path = "/state/firmware")
	public CustomResponse firmware( @RequestBody Request<Firmware> config) {
		//SAVING DATA FROM  Meter: CLE1054179003170        
		log.info("FIRMWARE : "+config.getBody().toString());

		Firmware fwAction = config.getBody();

		Job fwJob = jobService.save(new Job(Constants.JobNames.FIRMWARE.name(), fwAction.getType(), 
				Constants.JobStatus.READY_TO_RUN.name(), Constants.JobPriorty.FIRMWARE_UPDATE.getValue()));


		firmwareService.save(new Firmware(fwAction.getName(), fwAction.getType(), 
				fwAction.getVersion(), fwAction.getImage(), fwJob.getId(), fwAction.getImageId()));
		
		return new CustomResponse("Action queued for the meter", HttpStatus.ACCEPTED.value());
	}

	@PostMapping(path = "/state/scheduler")
	public CustomResponse action( @RequestBody Request<Job> action) {
		//SAVING DATA FROM  Meter: CLE1054179003170
		log.info("ACTION : "+action.getBody().toString());

		jobService.save(new Job(Constants.JobNames.GENERIC_JOB.name(), action.getBody().getType(), 
				Constants.JobStatus.READY_TO_RUN.name(), Constants.JobPriorty.GENERIC_ACTION.getValue()));

		return new CustomResponse("Action queued for the meter", HttpStatus.ACCEPTED.value());
	}

}
