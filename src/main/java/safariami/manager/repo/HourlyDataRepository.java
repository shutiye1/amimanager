package safariami.manager.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import safariami.manager.model.HourlyData;

public interface HourlyDataRepository extends JpaRepository<HourlyData, Long> {
	List<HourlyData> findByMeterId(Long meterId);
}
