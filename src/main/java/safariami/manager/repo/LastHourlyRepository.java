package safariami.manager.repo;

import org.springframework.data.repository.CrudRepository;

import safariami.manager.model.LastHourly;

public interface LastHourlyRepository extends CrudRepository<LastHourly, Long> {
}
