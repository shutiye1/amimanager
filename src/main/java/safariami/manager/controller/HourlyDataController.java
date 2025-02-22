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
import lombok.extern.slf4j.Slf4j;
import safariami.manager.model.HourlyData;
import safariami.manager.service.HourlyDataService;
import safariami.manager.util.CommonController;
import safariami.manager.util.error.CustomResponse;
import safariami.manager.util.error.ResourceNotFoundException;

@Slf4j
@RestController
public class HourlyDataController extends CommonController {
    
    @Autowired
    HourlyDataService hourlyDataService;
    
    @Operation(summary = "Get all hourly data entries", security = {@SecurityRequirement(name = "apiKey")})
    @GetMapping(path = "/hourlydata")
    public List<HourlyData> getAll() {
        log.info("Listing hourlyData");
        return hourlyDataService.findAll();
    }
    
    @Operation(summary = "Get a specific hourly data entry by ID", security = {@SecurityRequirement(name = "apiKey")})
    @GetMapping(path = "/hourlydata/{id}")
    public HourlyData getOne(@PathVariable("id") Long id) {
        log.info("Listing hourlyData: " + id);
        return hourlyDataService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No such HourlyData: " + id));
    }
    
    @Operation(summary = "Update an existing hourly data entry", security = {@SecurityRequirement(name = "apiKey")})
    @PutMapping(path = "/hourlydata/{id}")
    public HourlyData updateOne(@Valid @RequestBody HourlyData hourlyData, @PathVariable("id") Long id) {
        log.info("Update HourlyData: " + id);
        Optional<HourlyData> opHourlyData = hourlyDataService.findById(id);
        if (!opHourlyData.isPresent()) {
            return hourlyDataService.save(hourlyData);
        } else {
            hourlyData.setId(id);
            return hourlyDataService.save(hourlyData);
        }
    }
    
    @Operation(summary = "Delete an hourly data entry by ID", security = {@SecurityRequirement(name = "apiKey")})
    @DeleteMapping(path = "/hourlydata/{id}")
    public CustomResponse deleteOne(@PathVariable("id") Long id) {
        log.info("Deleting hourlyData: " + id);
        hourlyDataService.deleteById(id);
        return new CustomResponse("HourlyData Deleted Successfully", HttpStatus.ACCEPTED.value());
    }
    
    @Operation(summary = "Create a new hourly data entry", security = {@SecurityRequirement(name = "apiKey")})
    @PostMapping(path = "/hourlydata/create")
    public HourlyData createOne(@Valid @RequestBody HourlyData hourlyData) {
        log.info("Creating hourlyData");
        return hourlyDataService.save(hourlyData);
    }
}
