package safariami.manager.service;

import java.util.List;
import java.util.Optional;

import safariami.manager.model.ErrorLog;

public interface ErrorLogService {

    List<ErrorLog> findAll();
    Optional<ErrorLog> findById(Long id);
    void deleteById(Long id);
    ErrorLog save(ErrorLog errorLog);
}
