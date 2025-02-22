package safariami.manager.controller;

import java.util.List;
import java.util.Optional;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import safariami.manager.model.ActionLog;
import safariami.manager.service.ActionLogService;
import safariami.manager.util.CommonController;
import safariami.manager.util.error.CustomResponse;
import safariami.manager.util.error.ResourceNotFoundException;

@RestController
public class ActionLogController extends CommonController {

    private final Logger logger = LoggerFactory.getLogger(ActionLogController.class);

    @Autowired
    ActionLogService actionLogService;

    @Operation(summary = "Get all action logs", security = {@SecurityRequirement(name = "apiKey")})
    @GetMapping(path="/actionlogs")
    public List<ActionLog> getAll() {
        logger.info("Listing Actionlogs");
        return actionLogService.findAll();
    }

    @Operation(summary = "Get a specific action log by ID", security = {@SecurityRequirement(name = "apiKey")})
    @GetMapping(path="/actionlog/{id}")
    public ActionLog getOne(@PathVariable("id") Long id) {
        logger.info("Listing ActionLog: " + id);
        return actionLogService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No such ActionLog: " + id));
    }

    @Operation(summary = "Update an existing action log", security = {@SecurityRequirement(name = "apiKey")})
    @PutMapping(path="/actionlog/{id}")
    public ActionLog updateOne(@Valid @RequestBody ActionLog actionLog, @PathVariable("id") Long id) {
        logger.info("Update ActionLog: " + id);
        Optional<ActionLog> opActionLog = actionLogService.findById(id);
        if (!opActionLog.isPresent()) {
            return actionLogService.save(actionLog);
        } else {
            actionLog.setId(id);
            return actionLogService.save(actionLog);
        }
    }

    @Operation(summary = "Delete an action log by ID", security = {@SecurityRequirement(name = "apiKey")})
    @DeleteMapping(path="/actionlog/{id}")
    public CustomResponse deleteOne(@PathVariable("id") Long id) {
        logger.info("Deleting ActionLog: " + id);
        actionLogService.deleteById(id);
        return new CustomResponse("ActionLog Deleted Successfully", HttpStatus.ACCEPTED.value());
    }

    @Operation(summary = "Create a new action log", security = {@SecurityRequirement(name = "apiKey")})
    @PostMapping(path="/actionlog/create")
    public ActionLog createOne(@Valid @RequestBody ActionLog actionLog) {
        logger.info("Creating ActionLog");
        return actionLogService.save(actionLog);
    }
}
