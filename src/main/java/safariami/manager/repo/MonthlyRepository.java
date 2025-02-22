package safariami.manager.repo;


import org.springframework.data.repository.CrudRepository;

import safariami.manager.model.Monthly;

public interface MonthlyRepository extends CrudRepository<Monthly, Long> {
}
