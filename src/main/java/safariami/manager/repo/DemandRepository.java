package safariami.manager.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import safariami.manager.model.Demand;

public interface DemandRepository extends CrudRepository<Demand, Long> {
    @Transactional
    void deleteByMeterId(long id);
}
