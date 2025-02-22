package safariami.manager.service;

import java.util.List;
import java.util.Optional;

import safariami.manager.model.Meter;

public interface MeterService {
	public List<Meter> findAll();
    public Optional<Meter> findById(Long id);
    public void deleteById(Long id);
    public Meter save(Meter meter);
    public Meter findBySerialNo(String serialNo);
    public List<Meter> findByIsActive(boolean isActive);
    public int countByIsActive(boolean isActive);
    public List<Meter> findByIsActiveAndIsPrepayment(boolean isActive, boolean isPrepayment);
    public long countAll();
    public Meter findByCustomerSeqNo(String customerSeqNo);
    public String generateClearTamperToken(String serialNo) throws Exception;
    
}
