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
import safariami.manager.model.Metric;
import safariami.manager.service.MetricService;
import safariami.manager.util.CommonController;
import safariami.manager.util.error.CustomResponse;
import safariami.manager.util.error.ResourceNotFoundException;

@Slf4j
@RestController
public class MetricController extends CommonController {

    @Autowired
    private MetricService metricService;
    
    @Operation(summary = "Get all metrics", security = {@SecurityRequirement(name = "apiKey")})
    @GetMapping(path = "/metrics")
    public List<Metric> getAll() {
        log.info("Listing Metrics");
        return metricService.findAll();
    }
    
    @Operation(summary = "Get a specific metric by ID", security = {@SecurityRequirement(name = "apiKey")})
    @GetMapping(path = "/metric/{id}")
    public Metric getOne(@PathVariable("id") Long id) {
        log.info("Listing Metric: " + id);
        return metricService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No such Metric: " + id));
    }
    
    @Operation(summary = "Update an existing metric", security = {@SecurityRequirement(name = "apiKey")})
    @PutMapping(path = "/metric/{id}")
    public Metric updateOne(@Valid @RequestBody Metric metric, @PathVariable("id") Long id) {
        log.info("Update Metric: " + id);
        Optional<Metric> opMetric = metricService.findById(id);
        if (!opMetric.isPresent()) {
            return metricService.save(metric);
        } else {
            metric.setId(id);
            return metricService.save(metric);
        }
    }
    
    @Operation(summary = "Delete a metric by ID", security = {@SecurityRequirement(name = "apiKey")})
    @DeleteMapping(path = "/metric/{id}")
    public CustomResponse deleteOne(@PathVariable("id") Long id) {
        log.info("Deleting Metric: " + id);
        metricService.deleteById(id);
        return new CustomResponse("Metric Deleted Successfully", HttpStatus.ACCEPTED.value());
    }
    
    @Operation(summary = "Create a new metric", security = {@SecurityRequirement(name = "apiKey")})
    @PostMapping(path = "/metric/create")
    public Metric createOne(@Valid @RequestBody Metric metric) {
        log.info("Creating Metric");
        return metricService.save(metric);
    }
}
