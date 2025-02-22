package safariami.manager.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import safariami.manager.model.PrepaymentStatus;

public interface PrepaymentStatusRepository extends JpaRepository<PrepaymentStatus, Long> {
	PrepaymentStatus findBySerialNo(String serialNo);
	PrepaymentStatus findByPhoneNumber(String phoneNumber);
}
