package safariami.manager.controller;

import java.util.List;
import java.util.Optional;

import jakarta.validation.Valid;

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
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import safariami.manager.model.PrepaymentLog;
import safariami.manager.service.PrepaymentLogService;
import safariami.manager.util.CommonController;
import safariami.manager.util.error.CustomResponse;
import safariami.manager.util.error.ResourceNotFoundException;

@Slf4j
@RestController
public class PrepaymentLogController extends CommonController {
    @Autowired
    PrepaymentLogService prepaymentLogService;
    
    @Operation(summary = "Get all prepayment logs", security = {@SecurityRequirement(name = "apiKey")})
    @GetMapping(path = "/prepaymentlogs")
    public List<PrepaymentLog> getAll() {
        log.info("Listing PrepaymentLogs");
        return prepaymentLogService.findAll();
    }
    
    @Operation(summary = "Get prepayment log by ID", security = {@SecurityRequirement(name = "apiKey")})
    @GetMapping(path = "/prepaymentlog/{id}")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "PrepaymentLog retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "PrepaymentLog not found")
    })
    public PrepaymentLog getOne(@PathVariable("id") Long id) {
        log.info("Listing PrepaymentLog: " + id);
        return prepaymentLogService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No such PrepaymentLog: " + id));
    }
    
    @Operation(summary = "Update prepayment log by ID", security = {@SecurityRequirement(name = "apiKey")})
    @PutMapping(path = "/prepaymentlog/{id}")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "PrepaymentLog updated successfully"),
        @ApiResponse(responseCode = "404", description = "PrepaymentLog not found"),
        @ApiResponse(responseCode = "406", description = "Not Acceptable")
    })
    public PrepaymentLog updateOne(@Valid @RequestBody PrepaymentLog prepaymentLog, @PathVariable("id") Long id) {
        log.info("Update PrepaymentLog: " + id);
        Optional<PrepaymentLog> opPrepaymentLog = prepaymentLogService.findById(id);
        if (!opPrepaymentLog.isPresent()) {
            return prepaymentLogService.save(prepaymentLog);
        } else {
            prepaymentLog.setId(id);
            return prepaymentLogService.save(prepaymentLog);
        }
    }
    
    @Operation(summary = "Delete prepayment log by ID", security = {@SecurityRequirement(name = "apiKey")})
    @DeleteMapping(path = "/prepaymentlog/{id}")
    @ApiResponses({
        @ApiResponse(responseCode = "202", description = "PrepaymentLog deleted successfully"),
        @ApiResponse(responseCode = "404", description = "PrepaymentLog not found"),
        @ApiResponse(responseCode = "406", description = "Not Acceptable")
    })
    public CustomResponse deleteOne(@PathVariable("id") Long id) {
        log.info("Deleting PrepaymentLog: " + id);
        prepaymentLogService.deleteById(id);
        return new CustomResponse("PrepaymentLog Deleted Successfully", HttpStatus.ACCEPTED.value());
    }
    
    @Operation(summary = "Create a new prepayment log", security = {@SecurityRequirement(name = "apiKey")})
    @PostMapping(path = "/prepaymentlog/create")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "PrepaymentLog created successfully"),
        @ApiResponse(responseCode = "406", description = "Not Acceptable")
    })
    public PrepaymentLog createOne(@Valid @RequestBody PrepaymentLog prepaymentLog) {
        log.info("Creating PrepaymentLog");
        return prepaymentLogService.save(prepaymentLog);
    }
}
