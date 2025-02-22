package safariami.manager.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import safariami.manager.model.HourlyData;
import safariami.manager.repo.HourlyDataRepository;
import safariami.manager.service.HourlyDataService;

@Service
public class HourlyDataServiceImpl implements HourlyDataService {

	@Autowired
	HourlyDataRepository hourlyDataRepository;
	
	@Override
	public List<HourlyData> findAll() {
		return hourlyDataRepository.findAll();
	}

	@Override
	public Optional<HourlyData> findById(Long id) {
		return hourlyDataRepository.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		hourlyDataRepository.deleteById(id);		
	}

	@Override
	public void deleteAll() {
		hourlyDataRepository.deleteAll();		
	}

	@Override
	public HourlyData save(HourlyData hourlyData) {
		return hourlyDataRepository.save(hourlyData);
	}

	@Override
	public List<HourlyData> findByMeterId(Long meterId) {
		return hourlyDataRepository.findByMeterId(meterId);
	}

	@Override
	public long countAll() {
		return hourlyDataRepository.count();
	}

}
