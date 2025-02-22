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
import safariami.manager.model.Customer;
import safariami.manager.service.CustomerService;
import safariami.manager.util.CommonController;
import safariami.manager.util.error.CustomResponse;
import safariami.manager.util.error.ResourceNotFoundException;

@Slf4j
@RestController
public class CustomerController extends CommonController {

    @Autowired
    CustomerService customerService;

    @Operation(summary = "Get all customers", security = {@SecurityRequirement(name = "apiKey")})
    @GetMapping(path = "/customers")
    public List<Customer> getAll() {
        log.info("Listing customers");
        return customerService.findAll();
    }

    @Operation(summary = "Get a specific customer by ID", security = {@SecurityRequirement(name = "apiKey")})
    @GetMapping(path = "/customer/{id}")
    public Customer getOne(@PathVariable("id") Long id) {
        log.info("Listing customer: " + id);
        return customerService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No such Customer: " + id));
    }

    @Operation(summary = "Update an existing customer", security = {@SecurityRequirement(name = "apiKey")})
    @PutMapping(path = "/customer/{id}")
    public Customer updateOne(@Valid @RequestBody Customer customer, @PathVariable("id") Long id) {
        log.info("Update Customer: " + id);
        Optional<Customer> opCustomer = customerService.findById(id);
        if (!opCustomer.isPresent()) {
            return customerService.save(customer);
        } else {
            customer.setId(id);
            return customerService.save(customer);
        }
    }

    @Operation(summary = "Delete a customer by ID", security = {@SecurityRequirement(name = "apiKey")})
    @DeleteMapping(path = "/customer/{id}")
    public CustomResponse deleteOne(@PathVariable("id") Long id) {
        log.info("Deleting customer: " + id);
        customerService.deleteById(id);
        return new CustomResponse("Customer Deleted Successfully", HttpStatus.ACCEPTED.value());
    }

    @Operation(summary = "Create a new customer", security = {@SecurityRequirement(name = "apiKey")})
    @PostMapping(path = "/customer/create")
    public Customer createOne(@Valid @RequestBody Customer customer) {
        log.info("Creating customer");
        return customerService.save(customer);
    }
}
