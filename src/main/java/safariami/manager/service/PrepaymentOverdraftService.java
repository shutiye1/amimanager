package safariami.manager.service;

import java.util.List;
import java.util.Optional;

import safariami.manager.model.PrepaymentOverdraft;

public interface PrepaymentOverdraftService {
    public List<PrepaymentOverdraft> findAll();
    public Optional<PrepaymentOverdraft> findById(Long id);
    public void deleteById(Long id);
    public void deleteAll();
    public PrepaymentOverdraft save(PrepaymentOverdraft prepaymentOverdraft);
    public PrepaymentOverdraft findByCustomerId(String customerId);
    public List<PrepaymentOverdraft> findByStatus(String status);
    public long countAll();
}
