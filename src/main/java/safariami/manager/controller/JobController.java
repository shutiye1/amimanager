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
import safariami.manager.model.Job;
import safariami.manager.service.JobService;
import safariami.manager.util.CommonController;
import safariami.manager.util.error.CustomResponse;
import safariami.manager.util.error.ResourceNotFoundException;

@Slf4j
@RestController
public class JobController extends CommonController {

    @Autowired
    JobService jobService;

    @Operation(summary = "Get all jobs", security = {@SecurityRequirement(name = "apiKey")})
    @GetMapping(path = "/jobs")
    public List<Job> getAll() {
        log.info("Listing Jobs");
        return jobService.findAll();
    }

    @Operation(summary = "Get a specific job by ID", security = {@SecurityRequirement(name = "apiKey")})
    @GetMapping(path = "/job/{id}")
    public Job getOne(@PathVariable("id") Long id) {
        log.info("Listing Job: " + id);
        return jobService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No such Job: " + id));
    }

    @Operation(summary = "Update an existing job", security = {@SecurityRequirement(name = "apiKey")})
    @PutMapping(path = "/job/{id}")
    public Job updateOne(@Valid @RequestBody Job job, @PathVariable("id") Long id) {
        log.info("Update Job: " + id);
        Optional<Job> opJob = jobService.findById(id);
        if (!opJob.isPresent()) {
            return jobService.save(job);
        } else {
            job.setId(id);
            return jobService.save(job);
        }
    }

    @Operation(summary = "Delete a job by ID", security = {@SecurityRequirement(name = "apiKey")})
    @DeleteMapping(path = "/job/{id}")
    public CustomResponse deleteOne(@PathVariable("id") Long id) {
        log.info("Deleting Job: " + id);
        jobService.deleteById(id);
        return new CustomResponse("Job Deleted Successfully", HttpStatus.ACCEPTED.value());
    }

    @Operation(summary = "Create a new job", security = {@SecurityRequirement(name = "apiKey")})
    @PostMapping(path = "/job/create")
    public Job createOne(@Valid @RequestBody Job job) {
        log.info("Creating Job");
        return jobService.save(job);
    }
}
