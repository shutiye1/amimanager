package safariami.manager.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import safariami.manager.model.ErrorLog;

public interface ErrorLogRepository extends JpaRepository<ErrorLog, Long> {

}
