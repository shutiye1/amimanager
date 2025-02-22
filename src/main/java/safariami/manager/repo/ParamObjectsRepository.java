package safariami.manager.repo;



import org.springframework.data.jpa.repository.JpaRepository;

import safariami.manager.model.ParamObjects;

public interface ParamObjectsRepository extends JpaRepository<ParamObjects, Long> {
	ParamObjects findByObis(String obis);
}
