package safariami.manager.repo;

import org.springframework.data.repository.CrudRepository;

import safariami.manager.model.Bill;

public interface BillRepository extends CrudRepository<Bill, Long> {
}
