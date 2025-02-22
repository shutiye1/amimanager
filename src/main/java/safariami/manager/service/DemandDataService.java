package safariami.manager.service;

import java.util.List;
import java.util.Optional;

import safariami.manager.model.DemandData;

public interface DemandDataService {
    public List<DemandData> findAll();
    public Optional<DemandData> findById(Long id);
    public void deleteById(Long id);
    public void deleteAll();
    public DemandData save(DemandData demandData);
	public List<DemandData> findByMeterId(Long meterId);
    public long countAll();
}
