package safariami.manager.service;

import java.util.List;
import java.util.Optional;

import safariami.manager.model.Transaction;

public interface TransactionService {
    public List<Transaction> findAll();
    public Optional<Transaction> findById(Long id);
    public void deleteById(Long id);
    public void deleteAll();
    public Transaction save(Transaction transaction);
    public Transaction findByAccountId(String accountId);
    public long countAll();
}
