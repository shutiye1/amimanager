package safariami.manager.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import safariami.manager.model.Metric;
import safariami.manager.repo.MetricRepository;
import safariami.manager.service.MetricService;

@Service
public class MetricServiceImpl implements MetricService {

	@Autowired
	private MetricRepository metricRepository;

	@Override
	public List<Metric> findAll() {
		return metricRepository.findAll();
	}

	@Override
	public Optional<Metric> findById(Long id) {
		return metricRepository.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		metricRepository.deleteById(id);		
	}

	@Override
	public void deleteAll() {
		metricRepository.deleteAll();		
	}

	@Override
	public Metric save(Metric metric) {
		return metricRepository.save(metric);
	}
	
}
