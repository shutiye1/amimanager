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
import safariami.manager.model.PrepaymentStatus;
import safariami.manager.service.PrepaymentStatusService;
import safariami.manager.util.CommonController;
import safariami.manager.util.error.CustomResponse;
import safariami.manager.util.error.ResourceNotFoundException;

@Slf4j
@RestController
public class PrepaymentStatusController extends CommonController {

    @Autowired
    PrepaymentStatusService prepaymentStatusService;
    
    @Operation(summary = "Get all prepayment statuses", security = {@SecurityRequirement(name = "apiKey")})
    @GetMapping(path = "/prepaymentstatuss")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "List of all PrepaymentStatus")
    })
    public List<PrepaymentStatus> getAll() {
        log.info("Listing PrepaymentStatuss");
        return prepaymentStatusService.findAll();
    }
    
    @Operation(summary = "Get prepayment status by ID", security = {@SecurityRequirement(name = "apiKey")})
    @GetMapping(path = "/prepaymentstatus/{id}")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "PrepaymentStatus retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "PrepaymentStatus not found")
    })
    public PrepaymentStatus getOne(@PathVariable("id") Long id) {
        log.info("Listing PrepaymentStatus: " + id);
        return prepaymentStatusService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No such PrepaymentStatus: " + id));
    }
    
    @Operation(summary = "Update prepayment status by ID", security = {@SecurityRequirement(name = "apiKey")})
    @PutMapping(path = "/prepaymentstatus/{id}")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "PrepaymentStatus updated successfully"),
        @ApiResponse(responseCode = "404", description = "PrepaymentStatus not found"),
        @ApiResponse(responseCode = "406", description = "Not Acceptable")
    })
    public PrepaymentStatus updateOne(@Valid @RequestBody PrepaymentStatus prepaymentStatus, @PathVariable("id") Long id) {
        log.info("Update PrepaymentStatus: " + id);
        Optional<PrepaymentStatus> opPrepaymentStatus = prepaymentStatusService.findById(id);
        if (!opPrepaymentStatus.isPresent()) {
            return prepaymentStatusService.save(prepaymentStatus);
        } else {
            prepaymentStatus.setId(id);
            return prepaymentStatusService.save(prepaymentStatus);
        }
    }
    
    @Operation(summary = "Delete prepayment status by ID", security = {@SecurityRequirement(name = "apiKey")})
    @DeleteMapping(path = "/prepaymentstatus/{id}")
    @ApiResponses({
        @ApiResponse(responseCode = "202", description = "PrepaymentStatus deleted successfully"),
        @ApiResponse(responseCode = "404", description = "PrepaymentStatus not found"),
        @ApiResponse(responseCode = "406", description = "Not Acceptable")
    })
    public CustomResponse deleteOne(@PathVariable("id") Long id) {
        log.info("Deleting PrepaymentStatus: " + id);
        prepaymentStatusService.deleteById(id);
        return new CustomResponse("PrepaymentStatus Deleted Successfully", HttpStatus.ACCEPTED.value());
    }
    
    @Operation(summary = "Create a new prepayment status", security = {@SecurityRequirement(name = "apiKey")})
    @PostMapping(path = "/prepaymentstatus/create")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "PrepaymentStatus created successfully"),
        @ApiResponse(responseCode = "406", description = "Not Acceptable")
    })
    public PrepaymentStatus createOne(@Valid @RequestBody PrepaymentStatus prepaymentStatus) {
        log.info("Creating PrepaymentStatus");
        return prepaymentStatusService.save(prepaymentStatus);
    }
}
