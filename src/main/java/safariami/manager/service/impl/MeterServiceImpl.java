package safariami.manager.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import safariami.manager.model.Meter;
import safariami.manager.repo.MeterRepository;
import safariami.manager.service.MeterService;
import safariami.manager.service.STSTokenGeneratorService;

@Service
public class MeterServiceImpl implements MeterService {

	@Autowired
	private MeterRepository meterRepository;
	
	@Autowired
	private STSTokenGeneratorService tokenGeneratorService;

	@Override
	public List<Meter> findAll() {
		return meterRepository.findAll();
	}

	@Override
	public Optional<Meter> findById(Long id) {
		return meterRepository.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		meterRepository.deleteById(id);		
	}

	@Override
	public Meter save(Meter meter) {
		return meterRepository.save(meter);
	}

	@Override
	public Meter findBySerialNo(String serialNo) {
		return meterRepository.findBySerialNo(serialNo);
	}

	@Override
	public List<Meter> findByIsActive(boolean isActive) {
		return meterRepository.findByIsActive(isActive);
	}

	@Override
	public List<Meter> findByIsActiveAndIsPrepayment(boolean isActive, boolean isPrepayment) {
		return meterRepository.findByIsActiveAndIsPrepayment(isActive, isPrepayment);
	}

	@Override
	public int countByIsActive(boolean isActive) {
		return meterRepository.countByIsActive(isActive);
	}

	@Override
	public long countAll() {
		return meterRepository.count();
	}

	@Override
	public Meter findByCustomerSeqNo(String customerSeqNo) {
		return meterRepository.findByCustomerSeqNo(customerSeqNo);
	}

	@Override
	public String generateClearTamperToken(String serialNo) throws Exception {
		Meter meter = meterRepository.findBySerialNo(serialNo);

		if(meter == null) {
			throw new Exception("Unable to find meter with serial no: "+serialNo);
		}

		String tempSerialNo = serialNo;
		if(serialNo.length() > 11) {
			tempSerialNo = tempSerialNo.replaceAll("^CLE.?", "");
			tempSerialNo = tempSerialNo.replaceAll("0$", "");
		}

		String token = tokenGeneratorService.generateTamperToken(tempSerialNo);

		//log.info("Tamper Token ===>:"+token);

		return token;
	}
}
