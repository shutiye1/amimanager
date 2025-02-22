package safariami.manager.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import safariami.manager.model.PrepaymentOverdraft;
import safariami.manager.repo.PrepaymentOverdraftRepository;
import safariami.manager.service.PrepaymentOverdraftService;

@Service
public class PrepaymentOverdraftServiceImpl implements PrepaymentOverdraftService {
	
	@Autowired
	PrepaymentOverdraftRepository prepaymentOverdraftRepository;

	@Override
	public List<PrepaymentOverdraft> findAll() {
		return prepaymentOverdraftRepository.findAll();
	}

	@Override
	public Optional<PrepaymentOverdraft> findById(Long id) {
		return prepaymentOverdraftRepository.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		prepaymentOverdraftRepository.deleteById(id);		
	}

	@Override
	public void deleteAll() {
		prepaymentOverdraftRepository.deleteAll();		
	}

	@Override
	public PrepaymentOverdraft save(PrepaymentOverdraft prepaymentOverdraft) {
		return prepaymentOverdraftRepository.save(prepaymentOverdraft)	;	
	}

	@Override
	public PrepaymentOverdraft findByCustomerId(String customerId) {
		return prepaymentOverdraftRepository.findByCustomerId(customerId);
	}

	@Override
	public long countAll() {
		return prepaymentOverdraftRepository.count();
	}

	@Override
	public List<PrepaymentOverdraft> findByStatus(String status) {
		return prepaymentOverdraftRepository.findByStatus(status);
	}

}
