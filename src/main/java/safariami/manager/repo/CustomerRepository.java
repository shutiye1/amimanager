package safariami.manager.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import safariami.manager.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
	Customer findByCustomerId(String customerId);

}
