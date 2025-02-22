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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import safariami.manager.model.Role;
import safariami.manager.model.User;
import safariami.manager.service.RoleService;
import safariami.manager.service.UserService;
import safariami.manager.util.CommonController;
import safariami.manager.util.error.CustomResponse;
import safariami.manager.util.error.ResourceNotFoundException;

@RestController
public class UserController extends CommonController {

	private final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired UserService usersService;
	@Autowired RoleService roleService;
	
	@Operation(summary = "Get all users", security = {@SecurityRequirement(name = "apiKey")})
	@GetMapping(path = "/users")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "List of all users"),
		@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	public List<User> getAll() {
		logger.info("Listing users");
		return usersService.findAll();
	}
	
	@Operation(summary = "Get user by ID", security = {@SecurityRequirement(name = "apiKey")})
	@GetMapping(path = "/user/{id}")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "User retrieved successfully"),
		@ApiResponse(responseCode = "404", description = "User not found"),
		@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	public User getOne(@PathVariable("id") Long id) {
		logger.info("Listing User: " + id);
		return usersService.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No such User: " + id));
	}
	
	@Operation(summary = "Get user by username", security = {@SecurityRequirement(name = "apiKey")})
	@GetMapping(path = "/user/getuserbyusername")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "User retrieved successfully"),
		@ApiResponse(responseCode = "404", description = "User not found"),
		@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	public User getOneByUsername(@RequestParam("username") String userName) {
		logger.info("Listing User: " + userName);
		return usersService.findByUserName(userName)
				.orElseThrow(() -> new ResourceNotFoundException("No such User: " + userName));
	}
	
	@Operation(summary = "Update user by ID", security = {@SecurityRequirement(name = "apiKey")})
	@PutMapping(path = "/user/{id}")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "User updated successfully"),
		@ApiResponse(responseCode = "404", description = "User not found"),
		@ApiResponse(responseCode = "406", description = "Not Acceptable"),
		@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	public User updateOne(@Valid @RequestBody User user, @PathVariable("id") Long id) {
		logger.info("Update User: " + id);
		Optional<User> opUser = usersService.findById(id);
		if (!opUser.isPresent()) {
			User newUser = new User();
			newUser.setUserName(user.getUserName());
			newUser.setPassword(user.getPassword());
			newUser.setName(user.getName());
			newUser.setEnabled(user.isEnabled());
			Role role = roleService.findByName(user.getRole().getName())
					.orElseThrow(() -> new ResourceNotFoundException("No such Role: " + user.getRole().getName()));
			newUser.getRole().setId(role.getId());
			return usersService.save(newUser);
		} else {
			user.setId(id);
			return usersService.save(user);
		}
	}

	@Operation(summary = "Delete user by ID", security = {@SecurityRequirement(name = "apiKey")})
	@DeleteMapping(path = "/user/{id}")
	@ApiResponses({
		@ApiResponse(responseCode = "202", description = "User deleted successfully"),
		@ApiResponse(responseCode = "404", description = "User not found"),
		@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	public CustomResponse deleteOne(@PathVariable("id") Long id) {
		logger.info("Deleting User: " + id);
		usersService.deleteById(id);
		return new CustomResponse("User Deleted Successfully", HttpStatus.ACCEPTED.value());
	}
	
	@Operation(summary = "Create a new user", security = {@SecurityRequirement(name = "apiKey")})
	@PostMapping(path = "/user/create")
	@ApiResponses({
		@ApiResponse(responseCode = "201", description = "User created successfully"),
		@ApiResponse(responseCode = "406", description = "Not Acceptable"),
		@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	public User createOne(@Valid @RequestBody User user) {
		logger.info("Creating User");
		Role role = roleService.findByName(user.getRole().getName())
				.orElseThrow(() -> new ResourceNotFoundException("No such Role: " + user.getRole().getName()));
		user.getRole().setId(role.getId());
		return usersService.save(user);
	}
}
