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
import safariami.manager.model.DailyData;
import safariami.manager.service.DailyDataService;
import safariami.manager.util.CommonController;
import safariami.manager.util.error.CustomResponse;
import safariami.manager.util.error.ResourceNotFoundException;

@Slf4j
@RestController
public class DailyDataController extends CommonController {
    
    @Autowired
    DailyDataService dailyDataService;

    @Operation(summary = "Get all daily data entries", security = {@SecurityRequirement(name = "apiKey")})
    @GetMapping(path = "/dailydata")
    public List<DailyData> getAll() {
        log.info("Listing dailyData");
        return dailyDataService.findAll();
    }

    @Operation(summary = "Get a specific daily data entry by ID", security = {@SecurityRequirement(name = "apiKey")})
    @GetMapping(path = "/dailydata/{id}")
    public DailyData getOne(@PathVariable("id") Long id) {
        log.info("Listing dailyData: " + id);
        return dailyDataService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No such DailyData: " + id));
    }

    @Operation(summary = "Update an existing daily data entry", security = {@SecurityRequirement(name = "apiKey")})
    @PutMapping(path = "/dailydata/{id}")
    public DailyData updateOne(@Valid @RequestBody DailyData dailyData, @PathVariable("id") Long id) {
        log.info("Update DailyData: " + id);
        Optional<DailyData> opDailyData = dailyDataService.findById(id);
        if (!opDailyData.isPresent()) {
            return dailyDataService.save(dailyData);
        } else {
            dailyData.setId(id);
            return dailyDataService.save(dailyData);
        }
    }

    @Operation(summary = "Delete a daily data entry by ID", security = {@SecurityRequirement(name = "apiKey")})
    @DeleteMapping(path = "/dailydata/{id}")
    public CustomResponse deleteOne(@PathVariable("id") Long id) {
        log.info("Deleting dailyData: " + id);
        dailyDataService.deleteById(id);
        return new CustomResponse("DailyData Deleted Successfully", HttpStatus.ACCEPTED.value());
    }

    @Operation(summary = "Create a new daily data entry", security = {@SecurityRequirement(name = "apiKey")})
    @PostMapping(path = "/dailydata/create")
    public DailyData createOne(@Valid @RequestBody DailyData dailyData) {
        log.info("Creating dailyData");
        return dailyDataService.save(dailyData);
    }
}
