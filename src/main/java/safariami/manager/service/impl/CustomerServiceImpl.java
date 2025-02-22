package safariami.manager.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;

import safariami.manager.model.Customer;
import safariami.manager.repo.CustomerRepository;
import safariami.manager.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public List<Customer> findAll() {
		return customerRepository.findAll();
	}

	@Override
	public Optional<Customer> findById(Long id) {
		return customerRepository.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		customerRepository.deleteById(id);
	}

	@Override
	public void deleteAll() {
		customerRepository.deleteAll();
	}

	@Override
	public Customer save(Customer customer) {
		if(customer != null && !Strings.isNullOrEmpty(customer.getPassword())) {
			customer.setPassword(passwordEncoder.encode(customer.getPassword()));
		}
		return customerRepository.save(customer);
	}

	@Override
	public Customer findByCustomerId(String customerId) {
		return customerRepository.findByCustomerId(customerId);
	}

	@Override
	public long countAll() {
		return customerRepository.count();
	}

}
