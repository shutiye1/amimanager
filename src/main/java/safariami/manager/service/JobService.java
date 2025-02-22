package safariami.manager.service;

import java.util.List;
import java.util.Optional;

import safariami.manager.model.Job;

public interface JobService {

    List<Job> findAll();
    void deleteAll();
    Optional<Job> findById(Long id);
    void deleteById(Long id);
    Job save(Job job);
    List<Job> findJobByName(String name);
    List<Job> findJobByStatus(String status);
    List<Job> findJobByStatusAndName(String status, String name);
    long countAll();
}
