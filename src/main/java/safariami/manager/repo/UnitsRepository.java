package safariami.manager.repo;


import org.springframework.data.repository.CrudRepository;

import safariami.manager.model.Unit;

public interface UnitsRepository extends CrudRepository<Unit, Long> {
}
