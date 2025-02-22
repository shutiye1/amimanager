package safariami.manager.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import safariami.manager.model.DailyData;

public interface DailyDataRepository extends JpaRepository<DailyData, Long>{
	List<DailyData> findByMeterId(Long meterId);
}
