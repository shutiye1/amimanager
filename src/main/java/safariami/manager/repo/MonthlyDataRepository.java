package safariami.manager.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import safariami.manager.model.MonthlyData;

public interface MonthlyDataRepository extends JpaRepository<MonthlyData, Long> {
	List<MonthlyData> findByMeterId(Long meterId);
}
