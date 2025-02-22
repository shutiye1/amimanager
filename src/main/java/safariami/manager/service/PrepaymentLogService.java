package safariami.manager.service;

import java.util.List;
import java.util.Optional;

import safariami.manager.model.PrepaymentLog;

public interface PrepaymentLogService {

    List<PrepaymentLog> findAll();
    Optional<PrepaymentLog> findById(Long id);
    void deleteById(Long id);
    void deleteAll();
    PrepaymentLog save(PrepaymentLog actionLog);
    List<PrepaymentLog> findBySerialNo(String serialNo) throws Exception;
    List<PrepaymentLog> findByRetryAndSerialNo(boolean retry, String serialNo) throws Exception;
    List<PrepaymentLog> findTop10BySerialNo(String serialNo) throws Exception;
    long countAll();
}
