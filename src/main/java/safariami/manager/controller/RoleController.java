package safariami.manager.controller;

import java.util.List;
import java.util.Optional;

import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import safariami.manager.model.Role;
import safariami.manager.service.RoleService;
import safariami.manager.util.error.CustomResponse;
import safariami.manager.util.error.ResourceNotFoundException;


@RestController
@RequestMapping(path="/role")
public class RoleController {

	private final Logger logger = LoggerFactory.getLogger(RoleController.class);
	@Autowired RoleService roleService;
	
	@GetMapping(path="/list")
	public List<Role> getAll() {
		logger.info("Listing Authorities");
		
		return roleService.findAll();
	}
	
	@GetMapping(path="/{id}")
	public Role getOne(@PathVariable("id") Long id) {
		logger.info("Listing Authorities: "+id);
		
		return roleService.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No such Role: "+id));
	}
	
	@PostMapping(path="/add")
	public Role createOne(@Valid @RequestBody Role Role) {
		logger.info("Saving roles");
		
		return roleService.save(Role);
	}
	
	@PutMapping(path="/{id}")
	public Role updateOne(@Valid @RequestBody Role role, @PathVariable("id") Long id) {
		logger.info("Updating Role: "+id);
		
		Optional<Role> opAuth = roleService.findById(id);
		if(!opAuth.isPresent()) {
			Role newAuth = new Role();
			newAuth.setName(role.getName());
			return roleService.save(newAuth);
		}
		else {
			role.setId(id);
			return roleService.save(role);
		}
		
	}
	
	@DeleteMapping(path="/{id}")
	public CustomResponse deleteOne(@PathVariable("id") Long id) {
		logger.info("Deleting Role: "+id);
		
		roleService.deleteById(id);
		
		return new CustomResponse("Role Deleted Successfully", HttpStatus.ACCEPTED.value());
	}
}
