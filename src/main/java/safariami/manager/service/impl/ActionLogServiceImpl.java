package safariami.manager.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import safariami.manager.model.ActionLog;
import safariami.manager.repo.ActionLogRepository;
import safariami.manager.service.ActionLogService;

@Service
public class ActionLogServiceImpl implements ActionLogService {
	
	@Autowired
	private ActionLogRepository actionLogRepository;

	@Override
	public List<ActionLog> findAll() {
		return actionLogRepository.findAll();
	}

	@Override
	public Optional<ActionLog> findById(Long id) {
		return actionLogRepository.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		actionLogRepository.deleteById(id);
	}

	@Override
	public ActionLog save(ActionLog actionLog) {
		return actionLogRepository.save(actionLog);
	}


	@Override
	public List<ActionLog> findActionLogsByStatusAndRetry(String status, boolean retry) throws Exception {
		return actionLogRepository.findActionLogsByStatusAndRetry(status, retry);
	}

	@Override
	public List<ActionLog> findActionLogsByRetryAndSerialNo(boolean retry, String serialNo) throws Exception {
		return actionLogRepository.findActionLogsByRetryAndSerialNo(retry, serialNo);
	}


	@Override
	public List<ActionLog> findActionLogsBySerialNo(String serialNo) throws Exception {
		return actionLogRepository.findActionLogsBySerialNo(serialNo);
	}

	@Override
	public List<ActionLog> findActionLogsBySerialNoAndType(String serialNo, String type) throws Exception {
		return actionLogRepository.findActionLogsBySerialNoAndType(serialNo, type);
	}

	@Override
	public List<ActionLog> findActionLogsBySerialNoAndTypeAndCounterLessThan(String serialNo, String type, int counter) throws Exception {
		return actionLogRepository.findActionLogsBySerialNoAndTypeAndCounterLessThan(serialNo, type, counter);
	}

	@Override
	public List<ActionLog> findTop10ActionLogsBySerialNo(String serialNo) throws Exception {
		return actionLogRepository.findTop10ActionLogsBySerialNo(serialNo);
	}

	@Override
	public int countActionLogsByJobId(long jobId) throws Exception {
		return actionLogRepository.countActionLogsByJobId(jobId);
	}

	@Override
	public long countAll() {
		return actionLogRepository.count();
	}

	@Override
	public void deleteAll() {
		actionLogRepository.deleteAll();
	}

	@Override
	public int countActionLogsByJobIdAndCounterLessThan(long jobId, int counter) throws Exception {
		return actionLogRepository.countActionLogsByJobIdAndCounterLessThan(jobId, counter);
	}


}
