package safariami.manager.service;

import java.util.List;
import java.util.Optional;

import safariami.manager.model.Customer;

public interface CustomerService {

    public List<Customer> findAll();
    public Optional<Customer> findById(Long id);
    public void deleteById(Long id);
    public void deleteAll();
    public Customer save(Customer customer);
    public Customer findByCustomerId(String customerId);
    public long countAll();
}
