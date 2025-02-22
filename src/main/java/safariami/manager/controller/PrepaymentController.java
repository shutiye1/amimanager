package safariami.manager.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.apache.logging.log4j.util.Strings;
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
import safariami.manager.model.Prepayment;
import safariami.manager.model.PrepaymentDTO;
import safariami.manager.service.PrepaymentService;
import safariami.manager.util.CommonController;
import safariami.manager.util.error.CustomResponse;
import safariami.manager.util.error.ResourceNotFoundException;

@Slf4j
@RestController
public class PrepaymentController extends CommonController {
    @Autowired
    PrepaymentService prepaymentService;
    
    @Operation(summary = "Get all prepayments", security = {@SecurityRequirement(name = "apiKey")})
    @GetMapping(path = "/prepayments")
    public List<Prepayment> getAll() {
        log.info("Listing prepayments");
        return prepaymentService.findAll();
    }
    
    @Operation(summary = "Get prepayment by ID", security = {@SecurityRequirement(name = "apiKey")})
    @GetMapping(path = "/prepayment/{id}")
    public Prepayment getOne(@PathVariable("id") Long id) {
        log.info("Listing prepayment: " + id);
        return prepaymentService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No such Prepayment: " + id));
    }
    
    @Operation(summary = "Update prepayment by ID", security = {@SecurityRequirement(name = "apiKey")})
    @PutMapping(path = "/prepayment/{id}")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Prepayment Updated Successfully"),
        @ApiResponse(responseCode = "406", description = "Not Acceptable")
    })
    public CustomResponse updateOne(@Valid @RequestBody PrepaymentDTO ppDto, @PathVariable("id") Long id) {
        log.info("Update Prepayment: " + id);
        try {
            prepaymentService.save(ppDto, id);
            return new CustomResponse("Prepayment Updated Successfully", HttpStatus.OK.value());
        } catch (Exception e) {
            return new CustomResponse(e.getMessage(), HttpStatus.NOT_ACCEPTABLE.value());
        }
    }
    
    @Operation(summary = "Delete prepayment by ID", security = {@SecurityRequirement(name = "apiKey")})
    @DeleteMapping(path = "/prepayment/{id}")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Prepayment Deleted Successfully"),
        @ApiResponse(responseCode = "406", description = "Not Acceptable")
    })
    public CustomResponse deleteOne(@PathVariable("id") Long id) {
        log.info("Deleting prepayment: " + id);
        try {
            prepaymentService.deleteById(id);
            return new CustomResponse("Prepayment Deleted Successfully", HttpStatus.OK.value());
        } catch (Exception e) {
            return new CustomResponse(e.getMessage(), HttpStatus.NOT_ACCEPTABLE.value());
        }
    }
    
    @Operation(summary = "Create new prepayment", security = {@SecurityRequirement(name = "apiKey")})
    @PostMapping(path = "/prepayment/create")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Prepayment Created Successfully"),
        @ApiResponse(responseCode = "406", description = "Not Acceptable")
    })
    public CustomResponse createOne(@Valid @RequestBody PrepaymentDTO ppDto) {
        log.info("Creating prepayment");
        try {
            prepaymentService.create(ppDto);
            return new CustomResponse("Prepayment Created Successfully", HttpStatus.OK.value());
        } catch (Exception e) {
            return new CustomResponse(e.getMessage(), HttpStatus.NOT_ACCEPTABLE.value());
        }
    }
    
    @Operation(summary = "Get tamper token by serial number", security = {@SecurityRequirement(name = "apiKey")})
    @GetMapping(path = "/prepayment/generateTamperToken/{serialNo}")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Token Generation Successful"),
        @ApiResponse(responseCode = "406", description = "Not Acceptable")
    })
    public CustomResponse getTamperToken(@PathVariable("serialNo") String serialNo) {
        log.info("Get tampering token for meter: " + serialNo);
        try {
            if (Strings.isEmpty(serialNo)) {
                return new CustomResponse("Must provide valid meter serialNo!", HttpStatus.NOT_ACCEPTABLE.value());
            }
            String token = prepaymentService.generateTamperToken(serialNo);
            return new CustomResponse(token, "Token Generation Successful", HttpStatus.OK.value());
        } catch (Exception e) {
            return new CustomResponse(e.getMessage(), HttpStatus.NOT_ACCEPTABLE.value());
        }
    }
}
