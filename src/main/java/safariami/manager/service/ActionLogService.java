package safariami.manager.service;

import java.util.List;
import java.util.Optional;

import safariami.manager.model.ActionLog;

public interface ActionLogService {

    List<ActionLog> findAll();
    Optional<ActionLog> findById(Long id);
    void deleteById(Long id);
    void deleteAll();
    ActionLog save(ActionLog actionLog);
    List<ActionLog> findActionLogsBySerialNo(String serialNo) throws Exception;
    List<ActionLog> findActionLogsBySerialNoAndType(String serialNo, String type) throws Exception;
    List<ActionLog> findActionLogsBySerialNoAndTypeAndCounterLessThan(String serialNo, String type,int counter) throws Exception;
    List<ActionLog> findActionLogsByStatusAndRetry(String status, boolean retry) throws Exception;
    List<ActionLog> findActionLogsByRetryAndSerialNo(boolean retry, String serialNo) throws Exception;
    List<ActionLog> findTop10ActionLogsBySerialNo(String serialNo) throws Exception;
    int countActionLogsByJobIdAndCounterLessThan(long jobId, int counter) throws Exception;
    int countActionLogsByJobId(long jobId) throws Exception;
    long countAll();
}
