package safariami.manager.service;

import java.util.List;
import java.util.Optional;

import safariami.manager.model.MonthlyData;

public interface MonthlyDataService {
    public List<MonthlyData> findAll();
    public Optional<MonthlyData> findById(Long id);
    public void deleteById(Long id);
    public void deleteAll();
    public MonthlyData save(MonthlyData monthlyData);
	public List<MonthlyData> findByMeterId(Long meterId);
    public long countAll();
}
