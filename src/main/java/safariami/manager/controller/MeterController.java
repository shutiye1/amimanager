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

import com.google.common.base.Strings;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import safariami.manager.model.Meter;
import safariami.manager.service.MeterService;
import safariami.manager.util.CommonController;
import safariami.manager.util.error.CustomResponse;
import safariami.manager.util.error.ResourceNotFoundException;

@Slf4j
@RestController
public class MeterController extends CommonController {

    @Autowired
    private MeterService meterService;

    @Operation(summary = "Get all meters", security = {@SecurityRequirement(name = "apiKey")})
    @GetMapping(path = "/meters")
    public List<Meter> getAll() {
        log.info("Listing Meters");
        return meterService.findAll();
    }

    @Operation(summary = "Get a specific meter by ID", security = {@SecurityRequirement(name = "apiKey")})
    @GetMapping(path = "/meter/{id}")
    public Meter getOne(@PathVariable("id") Long id) {
        log.info("Listing Meter: " + id);
        return meterService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No such Meter: " + id));
    }

    @Operation(summary = "Update an existing meter", security = {@SecurityRequirement(name = "apiKey")})
    @PutMapping(path = "/meter/{id}")
    public Meter updateOne(@Valid @RequestBody Meter meter, @PathVariable("id") Long id) {
        log.info("Update Meter: " + id);
        Optional<Meter> opMeter = meterService.findById(id);
        if (!opMeter.isPresent()) {
            return meterService.save(meter);
        } else {
            meter.setId(id);
            return meterService.save(meter);
        }
    }

    @Operation(summary = "Delete a meter by ID", security = {@SecurityRequirement(name = "apiKey")})
    @DeleteMapping(path = "/meter/{id}")
    public CustomResponse deleteOne(@PathVariable("id") Long id) {
        log.info("Deleting Meter: " + id);
        meterService.deleteById(id);
        return new CustomResponse("Meter Deleted Successfully", HttpStatus.ACCEPTED.value());
    }

    @Operation(summary = "Create a new meter", security = {@SecurityRequirement(name = "apiKey")})
    @PostMapping(path = "/meter/create")
    public Meter createOne(@Valid @RequestBody Meter meter) {
        log.info("Creating Meter");
        return meterService.save(meter);
    }

    @Operation(summary = "Generate tamper token for a meter", security = {@SecurityRequirement(name = "apiKey")})
    @GetMapping(path = "/meter/generateTamperToken/{serialNo}")
    public CustomResponse getTamperToken(@PathVariable("serialNo") String serialNo) {
        log.info("Get tampering token for meter: " + serialNo);
        try {
            if (Strings.isNullOrEmpty(serialNo)) {
                return new CustomResponse("Must provide valid meter serialNo!", HttpStatus.NOT_ACCEPTABLE.value());
            }
            String token = meterService.generateClearTamperToken(serialNo);
            return new CustomResponse(token, "Token Generation Successful", HttpStatus.OK.value());
        } catch (Exception e) {
            return new CustomResponse(e.getMessage(), HttpStatus.NOT_ACCEPTABLE.value());
        }
    }
}
