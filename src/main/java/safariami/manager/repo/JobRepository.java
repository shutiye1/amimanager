package safariami.manager.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import safariami.manager.model.Job;

public interface JobRepository extends JpaRepository<Job, Long> {

	List<Job> findJobByName(String name);
	List<Job> findJobByStatus(String status);
	List<Job> findJobByStatusAndName(String status, String name);
}
