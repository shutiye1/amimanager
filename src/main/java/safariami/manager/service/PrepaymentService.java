package safariami.manager.service;

import java.util.List;
import java.util.Optional;

import safariami.manager.model.Prepayment;
import safariami.manager.model.PrepaymentDTO;

public interface PrepaymentService {
    public List<Prepayment> findAll();
    public Optional<Prepayment> findById(Long id);
    public Prepayment findBySerialNo(String serialNo);
    public void deleteById(Long id) throws Exception;
    public void deleteAll();
    public void save(PrepaymentDTO ppDto, long id) throws Exception;
    public Prepayment findByCustomerId(String cusomterId);
    public long countAll();
    public void create(PrepaymentDTO ppDto) throws Exception;
    public String generateTamperToken(String serialNo) throws Exception;
}
