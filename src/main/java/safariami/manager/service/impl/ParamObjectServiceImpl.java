package safariami.manager.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import safariami.manager.model.ParamObjects;
import safariami.manager.repo.ParamObjectsRepository;
import safariami.manager.service.ParamObjectService;

@Service
public class ParamObjectServiceImpl implements ParamObjectService {
	@Autowired
	ParamObjectsRepository paramObjectRepository;
	
	@Override
	public List<ParamObjects> findAll() {
		return paramObjectRepository.findAll();
	}

	@Override
	public Optional<ParamObjects> findById(Long id) {
		return paramObjectRepository.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		paramObjectRepository.deleteById(id);		
	}

	@Override
	public void deleteAll() {
		paramObjectRepository.deleteAll();		
	}

	@Override
	public ParamObjects save(ParamObjects paramObject) {
		return paramObjectRepository.save(paramObject);
	}

	@Override
	public long countAll() {
		return paramObjectRepository.count();
	}

	@Override
	public ParamObjects findByObis(String obis) {
		return paramObjectRepository.findByObis(obis);
	}
}
