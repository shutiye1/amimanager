package safariami.manager.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import safariami.manager.model.PrepaymentStatus;
import safariami.manager.repo.PrepaymentStatusRepository;
import safariami.manager.service.PrepaymentStatusService;

@Service
public class PrepaymentStatusServiceImpl implements PrepaymentStatusService {

	@Autowired
	PrepaymentStatusRepository prepaymentStatusRepository;
	
	@Override
	public List<PrepaymentStatus> findAll() {
		return prepaymentStatusRepository.findAll();
	}

	@Override
	public Optional<PrepaymentStatus> findById(Long id) {
		return prepaymentStatusRepository.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		prepaymentStatusRepository.deleteById(id);		
	}

	@Override
	public void deleteAll() {
		prepaymentStatusRepository.deleteAll();		
	}

	@Override
	public PrepaymentStatus save(PrepaymentStatus prepaymentStatus) {
		return prepaymentStatusRepository.save(prepaymentStatus);
	}

	@Override
	public PrepaymentStatus findBySerialNo(String serialNo) {
		return prepaymentStatusRepository.findBySerialNo(serialNo);
	}

	@Override
	public PrepaymentStatus findByPhoneNumber(String phoneNumber) {
		return prepaymentStatusRepository.findByPhoneNumber(phoneNumber);
	}

	@Override
	public long countAll() {
		return prepaymentStatusRepository.count();
	}

}
