package safariami.manager.repo;

import org.springframework.data.repository.CrudRepository;

import safariami.manager.model.BillView;

public interface BillViewRepository extends CrudRepository<BillView, Long> {
    BillView findBillViewByMeterId(long meterId);
}
