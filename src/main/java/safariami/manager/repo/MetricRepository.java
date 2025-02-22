package safariami.manager.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import safariami.manager.model.Metric;

public interface MetricRepository extends JpaRepository<Metric, Long> {
	Metric findMetricByAppId(long appId);
}
