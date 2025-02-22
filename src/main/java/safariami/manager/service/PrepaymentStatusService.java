package safariami.manager.service;

import java.util.List;
import java.util.Optional;

import safariami.manager.model.PrepaymentStatus;

public interface PrepaymentStatusService {
    List<PrepaymentStatus> findAll();
    Optional<PrepaymentStatus> findById(Long id);
    void deleteById(Long id);
    void deleteAll();
    PrepaymentStatus save(PrepaymentStatus prepaymentStatus);
    PrepaymentStatus findBySerialNo(String serialNo);
    PrepaymentStatus findByPhoneNumber(String phoneNumber);
    long countAll();
}
