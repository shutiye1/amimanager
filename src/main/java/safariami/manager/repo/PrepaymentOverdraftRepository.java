package safariami.manager.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import safariami.manager.model.PrepaymentOverdraft;;

public interface PrepaymentOverdraftRepository extends JpaRepository<PrepaymentOverdraft, Long>{
	PrepaymentOverdraft findByCustomerId(String customerId);
	List<PrepaymentOverdraft> findByStatus(String status);
}
