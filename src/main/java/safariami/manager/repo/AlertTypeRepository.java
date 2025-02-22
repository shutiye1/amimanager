package safariami.manager.repo;

import org.springframework.data.repository.CrudRepository;

import safariami.manager.model.AlertType;

public interface AlertTypeRepository extends CrudRepository<AlertType, Long> {
    AlertType findByAlertCode(long code);
    AlertType findByAlertCodeAndAlertType(long code, boolean type);
}
