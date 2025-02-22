package safariami.manager.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import safariami.manager.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	Transaction findByAccountId(String accountId);
}
