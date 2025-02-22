package safariami.manager.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import safariami.manager.model.MonthlyData;
import safariami.manager.repo.MonthlyDataRepository;
import safariami.manager.service.MonthlyDataService;

@Service
public class MonthlyDataServiceImpl implements MonthlyDataService {
	@Autowired
	MonthlyDataRepository monthlyDataRepository;
	
	@Override
	public List<MonthlyData> findAll() {
		return monthlyDataRepository.findAll();
	}

	@Override
	public Optional<MonthlyData> findById(Long id) {
		return monthlyDataRepository.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		monthlyDataRepository.deleteById(id);		
	}

	@Override
	public void deleteAll() {
		monthlyDataRepository.deleteAll();		
	}

	@Override
	public MonthlyData save(MonthlyData monthlyData) {
		return monthlyDataRepository.save(monthlyData);
	}

	@Override
	public List<MonthlyData> findByMeterId(Long meterId) {
		return monthlyDataRepository.findByMeterId(meterId);
	}

	@Override
	public long countAll() {
		return monthlyDataRepository.count();
	}
}
