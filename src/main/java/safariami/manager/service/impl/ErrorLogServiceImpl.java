package safariami.manager.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import safariami.manager.model.ErrorLog;
import safariami.manager.repo.ErrorLogRepository;
import safariami.manager.service.ErrorLogService;

@Service
public class ErrorLogServiceImpl implements ErrorLogService {
	
	@Autowired
	private ErrorLogRepository errorLogRepository;

	@Override
	public List<ErrorLog> findAll() {
		return errorLogRepository.findAll();
	}

	@Override
	public Optional<ErrorLog> findById(Long id) {
		return errorLogRepository.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		errorLogRepository.deleteById(id);	
	}

	@Override
	public ErrorLog save(ErrorLog errorLog) {
		return errorLogRepository.save(errorLog);
	}

}
