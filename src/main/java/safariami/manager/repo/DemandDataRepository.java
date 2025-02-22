package safariami.manager.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import safariami.manager.model.DemandData;

public interface DemandDataRepository extends JpaRepository<DemandData, Long>{
	List<DemandData> findByMeterId(Long meterId);
}
