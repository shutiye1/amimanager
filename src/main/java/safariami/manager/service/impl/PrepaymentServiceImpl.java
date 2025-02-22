package safariami.manager.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Strings;

import safariami.manager.model.Account;
import safariami.manager.model.Customer;
import safariami.manager.model.Meter;
import safariami.manager.model.Prepayment;
import safariami.manager.model.PrepaymentDTO;
import safariami.manager.repo.PrepaymentRepository;
import safariami.manager.service.AccountService;
import safariami.manager.service.CustomerService;
import safariami.manager.service.MeterService;
import safariami.manager.service.PrepaymentService;
import safariami.manager.service.STSTokenGeneratorService;
import safariami.manager.util.Constants;

@Service
public class PrepaymentServiceImpl implements PrepaymentService {
	
	@Autowired
	PrepaymentRepository prepaymentRepository;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	@Lazy
	AccountService accountService;
	
	@Autowired
	MeterService meterService;
	
	@Autowired
	STSTokenGeneratorService sTSTokenGeneratorService;

	@Override
	public List<Prepayment> findAll() {
		return prepaymentRepository.findAll();
	}

	@Override
	public Optional<Prepayment> findById(Long id) {
		return prepaymentRepository.findById(id);
	}

	@Transactional
	@Override
	public void deleteById(Long id) throws Exception {
		
		// Delete prepayment info
		Optional<Prepayment> opPrepayment = prepaymentRepository.findById(id);
		if(!opPrepayment.isPresent()) {
			throw new Exception("Unable to find Prepayment ID:"+id);
		}
		prepaymentRepository.deleteById(id);
		Prepayment prepayment = opPrepayment.get();
		
		// Delete customer info
		Customer customer = customerService.findByCustomerId(prepayment.getCustomerId());
		if(customer == null) {
			throw new Exception("Unable to find customer with ID:"+prepayment.getCustomerId());
		}
		customerService.deleteById(customer.getId());
		
		// Delete account info
		Account account = accountService.findByAccountId(prepayment.getCustomerId());
		if(account == null) {
			throw new Exception("Unable to find account with ID:"+prepayment.getCustomerId());
		}
		accountService.deleteById(account.getId());
		
		Meter meter = meterService.findByCustomerSeqNo(prepayment.getCustomerId());
		
		if(meter == null) {
			throw new Exception("Unable to find customer assigned meter");
		}
		
		meter.setPrepayment(false);
		meterService.save(meter);
	}

	@Override
	public void deleteAll() {
		prepaymentRepository.deleteAll();
	}

	@Transactional
	@Override
	public void save(PrepaymentDTO ppDto, long id) throws Exception {
		
		// Update prepayment info
		Optional<Prepayment> opPrepayment = prepaymentRepository.findById(id);
		if(!opPrepayment.isPresent()) {
			throw new Exception("Unable to find Prepayment ID:"+id);
		}
		prepaymentRepository.save(opPrepayment.get());
		
		// Update customer info
		Customer customer = customerService.findByCustomerId(ppDto.getCustomerId());
		if(customer == null) {
			throw new Exception("Unable to find customer with ID:"+ppDto.getCustomerId());
		}
		if(!Strings.isNullOrEmpty(ppDto.getFirstName())) customer.setFirstName(ppDto.getFirstName());
		if(!Strings.isNullOrEmpty(ppDto.getMiddleName())) customer.setMiddleName(ppDto.getMiddleName());
		if(!Strings.isNullOrEmpty(ppDto.getLastName())) customer.setLastName(ppDto.getLastName());
		if(!Strings.isNullOrEmpty(ppDto.getEmail())) customer.setEmail(ppDto.getEmail());
		if(!Strings.isNullOrEmpty(ppDto.getPhone())) customer.setPhone(ppDto.getPhone());
		if(!Strings.isNullOrEmpty(ppDto.getCustomerId())) customer.setCustomerId(ppDto.getCustomerId());
		if(!Strings.isNullOrEmpty(ppDto.getPassword())) customer.setPassword(ppDto.getPassword());
		if(!Strings.isNullOrEmpty(ppDto.getAddress())) customer.setAddress(ppDto.getAddress());
		customerService.save(customer);
		
		// Update account info
		Account account = accountService.findByAccountId(ppDto.getCustomerId());
		if(account == null) {
			throw new Exception("Unable to find account with ID:"+ppDto.getCustomerId());
		}
		//if(!Strings.isNullOrEmpty(ppDto.getCustomerId())) account.setAccountId(Constants.AccountPrefix.E.name()+ppDto.getCustomerId());
		accountService.save(account);
	
	}

	@Override
	public Prepayment findByCustomerId(String customerId) {
		return prepaymentRepository.findByCustomerId(customerId);
	}

	@Override
	public long countAll() {
		return prepaymentRepository.count();
	}

	@Transactional
	@Override
	public void create(PrepaymentDTO ppDto) throws Exception {
		Meter meter = meterService.findByCustomerSeqNo(ppDto.getCustomerId());
		
		if(meter == null) {
			throw new Exception("Unable to find customer assigned meter");
		}
		
		customerService.save(new Customer(ppDto.getFirstName(), ppDto.getMiddleName(), ppDto.getLastName(), ppDto.getEmail(),
				ppDto.getPhone(), ppDto.getCustomerId(), ppDto.getPassword(), ppDto.getAddress()));
		
		accountService.save(new Account(new BigDecimal("0.00"), ppDto.getCustomerId()));
		
		if(ppDto.getMaxNotification() == null) ppDto.setMaxNotification(Constants.MAX_NOTIFICATION);
		
		prepaymentRepository.save(new Prepayment(ppDto.getCustomerId(), ppDto.getIntialReading(), 
				ppDto.getOverdraftThreshold(), ppDto.getMaxNotification(), ppDto.getRatePlan(), 
				meter.getSerialNo(), ppDto.getPhone(), meter.getPhoneNumber(), meter.isAMI()));
	
		meter.setPrepayment(true);
		meterService.save(meter);
	}

	@Override
	public Prepayment findBySerialNo(String serialNo) {
		return prepaymentRepository.findBySerialNo(serialNo);
	}

	@Override
	public String generateTamperToken(String serialNo) throws Exception {
		Meter meter = meterService.findBySerialNo(serialNo);
		
		if(meter == null) {
			throw new Exception("Unable to find customer assigned meter");
		}
		return sTSTokenGeneratorService.generateTamperToken(serialNo);
	}

}
