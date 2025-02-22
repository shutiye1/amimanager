package safariami.manager.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import safariami.manager.model.PrepaymentLog;
import safariami.manager.repo.PrepaymentLogRepository;
import safariami.manager.service.PrepaymentLogService;

@Service
public class PrepaymentLogServiceImpl implements PrepaymentLogService {
	
	@Autowired
	PrepaymentLogRepository prepaymentLogRepository;

	@Override
	public List<PrepaymentLog> findAll() {
		return prepaymentLogRepository.findAll();
	}

	@Override
	public Optional<PrepaymentLog> findById(Long id) {
		return prepaymentLogRepository.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		prepaymentLogRepository.deleteById(id);		
	}

	@Override
	public void deleteAll() {
		prepaymentLogRepository.deleteAll();		
	}

	@Override
	public PrepaymentLog save(PrepaymentLog prepaymentLog) {
		return prepaymentLogRepository.save(prepaymentLog);
	}

	@Override
	public List<PrepaymentLog> findBySerialNo(String serialNo) throws Exception {
		return prepaymentLogRepository.findBySerialNo(serialNo);
	}

	@Override
	public List<PrepaymentLog> findByRetryAndSerialNo(boolean retry, String serialNo) throws Exception {
		return prepaymentLogRepository.findByRetryAndSerialNo(retry, serialNo);
	}

	@Override
	public List<PrepaymentLog> findTop10BySerialNo(String serialNo) throws Exception {
		return prepaymentLogRepository.findTop10BySerialNo(serialNo);
	}

	@Override
	public long countAll() {
		return prepaymentLogRepository.count();
	}


}
