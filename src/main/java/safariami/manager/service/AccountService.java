package safariami.manager.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import safariami.manager.model.Account;

public interface AccountService {
    public List<Account> findAll();
    public Optional<Account> findById(Long id);
    public void deleteById(Long id);
    public void deleteAll();
    public Account save(Account account);
    public Account findByAccountId(String accountId);
    public long countAll();
    public void deposit(BigDecimal amount, String accountId) throws Exception;
    public Account withraw(BigDecimal amount, String accountId) throws Exception;
    public Account balance(String accountId) throws Exception;
}
