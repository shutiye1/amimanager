package safariami.manager.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import safariami.manager.model.PrepaymentLog;

public interface PrepaymentLogRepository extends JpaRepository<PrepaymentLog, Long> {

	List<PrepaymentLog> findBySerialNo(String serialNo) throws Exception;
    List<PrepaymentLog> findByRetryAndSerialNo(boolean retry, String serialNo) throws Exception;
    List<PrepaymentLog> findTop10BySerialNo(String serialNo) throws Exception;
}
