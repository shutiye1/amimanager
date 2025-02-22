package safariami.manager.service;

import java.util.List;
import java.util.Optional;

import safariami.manager.model.Metric;

public interface MetricService {

	public List<Metric> findAll();
	public Optional<Metric> findById(Long id);
    public void deleteById(Long id);
    public void deleteAll();
	public Metric save(Metric metric);
}
