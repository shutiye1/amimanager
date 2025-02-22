package safariami.manager.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import safariami.manager.model.DailyData;
import safariami.manager.repo.DailyDataRepository;
import safariami.manager.service.DailyDataService;

@Service
public class DailyDataServiceImpl implements DailyDataService {
	@Autowired
	DailyDataRepository dailyDataRepository;
	
	@Override
	public List<DailyData> findAll() {
		return dailyDataRepository.findAll();
	}

	@Override
	public Optional<DailyData> findById(Long id) {
		return dailyDataRepository.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		dailyDataRepository.deleteById(id);		
	}

	@Override
	public void deleteAll() {
		dailyDataRepository.deleteAll();		
	}

	@Override
	public DailyData save(DailyData dailyData) {
		return dailyDataRepository.save(dailyData);
	}

	@Override
	public List<DailyData> findByMeterId(Long meterId) {
		return dailyDataRepository.findByMeterId(meterId);
	}

	@Override
	public long countAll() {
		return dailyDataRepository.count();
	}
}
