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
import safariami.manager.model.ParamObjects;
import safariami.manager.service.ParamObjectService;
import safariami.manager.util.CommonController;
import safariami.manager.util.error.CustomResponse;
import safariami.manager.util.error.ResourceNotFoundException;

@Slf4j
@RestController
public class ParamObjectController extends CommonController {
    @Autowired
    ParamObjectService paramObjectService;
    
    @Operation(summary = "Get all param objects", security = {@SecurityRequirement(name = "apiKey")})
    @GetMapping(path = "/paramobject")
    public List<ParamObjects> getAll() {
        log.info("Listing paramObject");
        return paramObjectService.findAll();
    }
    
    @Operation(summary = "Get param object by OBIS code", security = {@SecurityRequirement(name = "apiKey")})
    @GetMapping(path = "/paramobject/{obis}")
    public ParamObjects getByObis(@PathVariable("obis") String obis) {
        log.info("Listing paramObject: " + obis);
        return paramObjectService.findByObis(obis);
    }
    
    @Operation(summary = "Get param object by ID", security = {@SecurityRequirement(name = "apiKey")})
    @GetMapping(path = "/paramobject/{id}")
    public ParamObjects getOne(@PathVariable("id") Long id) {
        log.info("Listing paramObject: " + id);
        return paramObjectService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No such ParamObjects: " + id));
    }
    
    @Operation(summary = "Update param object by ID", security = {@SecurityRequirement(name = "apiKey")})
    @PutMapping(path = "/paramobject/{id}")
    public ParamObjects updateOne(@Valid @RequestBody ParamObjects paramObject, @PathVariable("id") Long id) {
        log.info("Update ParamObjects: " + id);
        Optional<ParamObjects> opParamObjects = paramObjectService.findById(id);
        if (!opParamObjects.isPresent()) {
            return paramObjectService.save(paramObject);
        } else {
            paramObject.setId(id);
            return paramObjectService.save(paramObject);
        }
    }
    
    @Operation(summary = "Delete param object by ID", security = {@SecurityRequirement(name = "apiKey")})
    @DeleteMapping(path = "/paramobject/{id}")
    public CustomResponse deleteOne(@PathVariable("id") Long id) {
        log.info("Deleting paramObject: " + id);
        paramObjectService.deleteById(id);
        return new CustomResponse("ParamObjects Deleted Successfully", HttpStatus.ACCEPTED.value());
    }
    
    @Operation(summary = "Create new param object", security = {@SecurityRequirement(name = "apiKey")})
    @PostMapping(path = "/paramobject/create")
    public ParamObjects createOne(@Valid @RequestBody ParamObjects paramObject) {
        log.info("Creating paramObject");
        return paramObjectService.save(paramObject);
    }
}
