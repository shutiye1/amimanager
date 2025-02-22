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
import safariami.manager.model.MonthlyData;
import safariami.manager.service.MonthlyDataService;
import safariami.manager.util.CommonController;
import safariami.manager.util.error.CustomResponse;
import safariami.manager.util.error.ResourceNotFoundException;

@Slf4j
@RestController
public class MonthlyDataController extends CommonController {
    @Autowired
    MonthlyDataService monthlyDataService;
    
    @Operation(summary = "Get all monthly data", security = {@SecurityRequirement(name = "apiKey")})
    @GetMapping(path = "/monthlydata")
    public List<MonthlyData> getAll() {
        log.info("Listing monthlyData");
        return monthlyDataService.findAll();
    }
    
    @Operation(summary = "Get monthly data by ID", security = {@SecurityRequirement(name = "apiKey")})
    @GetMapping(path = "/monthlydata/{id}")
    public MonthlyData getOne(@PathVariable("id") Long id) {
        log.info("Listing monthlyData: " + id);
        return monthlyDataService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No such MonthlyData: " + id));
    }
    
    @Operation(summary = "Update monthly data by ID", security = {@SecurityRequirement(name = "apiKey")})
    @PutMapping(path = "/monthlydata/{id}")
    public MonthlyData updateOne(@Valid @RequestBody MonthlyData monthlyData, @PathVariable("id") Long id) {
        log.info("Update MonthlyData: " + id);
        Optional<MonthlyData> opMonthlyData = monthlyDataService.findById(id);
        if (!opMonthlyData.isPresent()) {
            return monthlyDataService.save(monthlyData);
        } else {
            monthlyData.setId(id);
            return monthlyDataService.save(monthlyData);
        }
    }
    
    @Operation(summary = "Delete monthly data by ID", security = {@SecurityRequirement(name = "apiKey")})
    @DeleteMapping(path = "/monthlydata/{id}")
    public CustomResponse deleteOne(@PathVariable("id") Long id) {
        log.info("Deleting monthlyData: " + id);
        monthlyDataService.deleteById(id);
        return new CustomResponse("MonthlyData Deleted Successfully", HttpStatus.ACCEPTED.value());
    }
    
    @Operation(summary = "Create new monthly data", security = {@SecurityRequirement(name = "apiKey")})
    @PostMapping(path = "/monthlydata/create")
    public MonthlyData createOne(@Valid @RequestBody MonthlyData monthlyData) {
        log.info("Creating monthlyData");
        return monthlyDataService.save(monthlyData);
    }
}
