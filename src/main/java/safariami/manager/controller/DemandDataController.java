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
import safariami.manager.model.DemandData;
import safariami.manager.service.DemandDataService;
import safariami.manager.util.CommonController;
import safariami.manager.util.error.CustomResponse;
import safariami.manager.util.error.ResourceNotFoundException;

@Slf4j
@RestController
public class DemandDataController extends CommonController {
    
    @Autowired
    DemandDataService demandDataService;
    
    @Operation(summary = "Get all demand data entries", security = {@SecurityRequirement(name = "apiKey")})
    @GetMapping(path = "/demanddata")
    public List<DemandData> getAll() {
        log.info("Listing demandData");
        return demandDataService.findAll();
    }
    
    @Operation(summary = "Get a specific demand data entry by ID", security = {@SecurityRequirement(name = "apiKey")})
    @GetMapping(path = "/demanddata/{id}")
    public DemandData getOne(@PathVariable("id") Long id) {
        log.info("Listing demandData: " + id);
        return demandDataService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No such DemandData: " + id));
    }
    
    @Operation(summary = "Update an existing demand data entry", security = {@SecurityRequirement(name = "apiKey")})
    @PutMapping(path = "/demanddata/{id}")
    public DemandData updateOne(@Valid @RequestBody DemandData demandData, @PathVariable("id") Long id) {
        log.info("Update DemandData: " + id);
        Optional<DemandData> opDemandData = demandDataService.findById(id);
        if (!opDemandData.isPresent()) {
            return demandDataService.save(demandData);
        } else {
            demandData.setId(id);
            return demandDataService.save(demandData);
        }
    }
    
    @Operation(summary = "Delete a demand data entry by ID", security = {@SecurityRequirement(name = "apiKey")})
    @DeleteMapping(path = "/demanddata/{id}")
    public CustomResponse deleteOne(@PathVariable("id") Long id) {
        log.info("Deleting demandData: " + id);
        demandDataService.deleteById(id);
        return new CustomResponse("DemandData Deleted Successfully", HttpStatus.ACCEPTED.value());
    }
    
    @Operation(summary = "Create a new demand data entry", security = {@SecurityRequirement(name = "apiKey")})
    @PostMapping(path = "/demanddata/create")
    public DemandData createOne(@Valid @RequestBody DemandData demandData) {
        log.info("Creating demandData");
        return demandDataService.save(demandData);
    }
}
