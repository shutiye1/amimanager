package safariami.manager.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import safariami.manager.model.Role;
import safariami.manager.model.User;

import jakarta.annotation.PostConstruct;


@Service
public class DBInitializer {
	
	private Logger logger = LoggerFactory.getLogger(DBInitializer.class);
	
	@Autowired
	UserService usersService;
	
	@Autowired
	RoleService roleService;
	
	@PostConstruct
	public void initDB() {
		logger.info("Starting database initialization...");
		
		User user = new User();
		user.setUserName("root");
		user.setPassword("abcd1234");
		user.setEnabled(true);
		
		Role role = new Role();
		role.setName("USER");
		//authorityService.save(authority);
		
		user.setRole(role);
		
		//usersService.save(user);
		
		logger.info("Database initialization finished.");
	}

}
