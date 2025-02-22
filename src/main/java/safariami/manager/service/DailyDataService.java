package safariami.manager.service;

import java.util.List;
import java.util.Optional;

import safariami.manager.model.DailyData;

public interface DailyDataService {
    public List<DailyData> findAll();
    public Optional<DailyData> findById(Long id);
    public void deleteById(Long id);
    public void deleteAll();
    public DailyData save(DailyData dailyData);
	public List<DailyData> findByMeterId(Long meterId);
    public long countAll();
}
