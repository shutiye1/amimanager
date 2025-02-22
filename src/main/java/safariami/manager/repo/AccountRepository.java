package safariami.manager.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import safariami.manager.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	Account findByAccountId(String accountId);
}
