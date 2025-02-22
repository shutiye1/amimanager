package safariami.manager.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import safariami.manager.model.Meter;

public interface MeterRepository extends JpaRepository<Meter, Long> {
    Meter findBySerialNo(String serialNo);
    List<Meter> findByIsActive(boolean isActive);
    int countByIsActive(boolean isActive);
    Meter findByCustomerSeqNo(String customerSeqNo);
    List<Meter> findByIsActiveAndIsPrepayment(boolean isActive, boolean isPrepayment);
}
