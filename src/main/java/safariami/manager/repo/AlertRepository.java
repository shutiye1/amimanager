package safariami.manager.repo;


import org.springframework.data.repository.CrudRepository;

import safariami.manager.model.Alert;

import java.util.Date;

public interface AlertRepository extends CrudRepository<Alert, Long> {
    Alert findAlertBySerialNoAndAlertCodeAndCaptureTime(String serialNo, int alertCode, Date captureTime);
}
