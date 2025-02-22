package safariami.manager.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import safariami.manager.model.DemandData;
import safariami.manager.repo.DemandDataRepository;
import safariami.manager.service.DemandDataService;

@Service
public class DemandDataServiceImpl implements DemandDataService {
	@Autowired
	DemandDataRepository demandDataRepository;
	
	@Override
	public List<DemandData> findAll() {
		return demandDataRepository.findAll();
	}

	@Override
	public Optional<DemandData> findById(Long id) {
		return demandDataRepository.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		demandDataRepository.deleteById(id);		
	}

	@Override
	public void deleteAll() {
		demandDataRepository.deleteAll();		
	}

	@Override
	public DemandData save(DemandData demandData) {
		return demandDataRepository.save(demandData);
	}

	@Override
	public List<DemandData> findByMeterId(Long meterId) {
		return demandDataRepository.findByMeterId(meterId);
	}

	@Override
	public long countAll() {
		return demandDataRepository.count();
	}
}
