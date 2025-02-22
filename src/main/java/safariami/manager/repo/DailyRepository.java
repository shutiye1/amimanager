package safariami.manager.repo;


import org.springframework.data.repository.CrudRepository;

import safariami.manager.model.Daily;

public interface DailyRepository extends CrudRepository<Daily, Long> {
}
