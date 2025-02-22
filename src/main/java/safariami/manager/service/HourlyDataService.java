package safariami.manager.service;

import java.util.List;
import java.util.Optional;

import safariami.manager.model.HourlyData;

public interface HourlyDataService {
    public List<HourlyData> findAll();
    public Optional<HourlyData> findById(Long id);
    public void deleteById(Long id);
    public void deleteAll();
    public HourlyData save(HourlyData hourlyData);
	public List<HourlyData> findByMeterId(Long meterId);
    public long countAll();
}
