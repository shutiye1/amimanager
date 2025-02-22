package safariami.manager.service;

import java.util.List;
import java.util.Optional;

import safariami.manager.model.ParamObjects;

public interface ParamObjectService {
    public List<ParamObjects> findAll();
    public Optional<ParamObjects> findById(Long id);
    public void deleteById(Long id);
    public void deleteAll();
    public ParamObjects save(ParamObjects paramObjectData);
	public ParamObjects findByObis(String obis);
    public long countAll();
}
