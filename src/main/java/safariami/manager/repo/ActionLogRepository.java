package safariami.manager.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import safariami.manager.model.ActionLog;

public interface ActionLogRepository extends JpaRepository<ActionLog, Long> {

	List<ActionLog> findActionLogsBySerialNo(String serialNo) throws Exception;
	List<ActionLog> findActionLogsBySerialNoAndType(String serialNo, String type) throws Exception;
    List<ActionLog> findActionLogsByStatusAndRetry(String status, boolean retry) throws Exception;
    List<ActionLog> findActionLogsBySerialNoAndTypeAndCounterLessThan(String serialNo, String type,int counter) throws Exception;
    List<ActionLog> findActionLogsByRetryAndSerialNo(boolean retry, String serialNo) throws Exception;
    List<ActionLog> findTop10ActionLogsBySerialNo(String serialNo) throws Exception;
    int countActionLogsByJobId(long jobId) throws Exception;
	int countActionLogsByJobIdAndCounterLessThan(long jobId, int counter);
}
