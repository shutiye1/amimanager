package safariami.manager.repo;


import org.springframework.data.repository.CrudRepository;

import safariami.manager.model.Hourly;

public interface HourlyRepository extends CrudRepository<Hourly, Long> {
}
