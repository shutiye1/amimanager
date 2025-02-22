package safariami.manager.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import safariami.manager.model.Prepayment;;

public interface PrepaymentRepository extends JpaRepository<Prepayment, Long>{
	Prepayment findBySerialNo(String serialNo);
	Prepayment findByCustomerId(String customerId);
}
