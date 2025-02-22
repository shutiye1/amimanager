package safariami.manager.repo;

import org.springframework.data.repository.CrudRepository;

import safariami.manager.model.AlertLog;

public interface AlertLogRepository extends CrudRepository<AlertLog, Long> {
}
