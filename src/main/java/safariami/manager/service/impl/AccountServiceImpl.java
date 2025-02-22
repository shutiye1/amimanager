package safariami.manager.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import safariami.manager.model.Account;
import safariami.manager.model.ActionLog;
import safariami.manager.model.Prepayment;
import safariami.manager.model.Transaction;
import safariami.manager.repo.AccountRepository;
import safariami.manager.service.AccountService;
import safariami.manager.service.ActionLogService;
import safariami.manager.service.PrepaymentService;
import safariami.manager.service.STSTokenGeneratorService;
import safariami.manager.service.TransactionService;
import safariami.manager.util.Constants;
import safariami.manager.util.SMPPHelper;

@Slf4j
@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	@Lazy
	private PrepaymentService prepaymentService;

	@Autowired
	private STSTokenGeneratorService tokenGeneratorService;
	
	@Autowired
	private ActionLogService actionLogService;
	
	@Override
	public List<Account> findAll() {
		return accountRepository.findAll();
	}

	@Override
	public Optional<Account> findById(Long id) {
		return accountRepository.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		accountRepository.deleteById(id);
	}

	@Override
	public void deleteAll() {
		accountRepository.deleteAll();
	}

	@Override
	public Account save(Account account) {
		boolean genAccountId = false;
		if(account == null || Strings.isEmpty(account.getAccountId())) {
			genAccountId = true;
		}
		Account newAccount = accountRepository.save(account);
		
		if(genAccountId) {
			newAccount.setAccountId(newAccount.getId()+"");
			newAccount = accountRepository.save(account);
		}
		
		return newAccount;
	}

	@Override
	public Account findByAccountId(String accountId) {
		return accountRepository.findByAccountId(accountId);
	}

	@Override
	public long countAll() {
		return accountRepository.count();
	}
	
	@Transactional
	@Override
	public void deposit(BigDecimal amount, String accountId) throws Exception {
		if(amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
			throw new Exception("Deposit amount must be > 0");
		}
		
		Account account = accountRepository.findByAccountId(accountId);
		
		if(account == null) {
			throw new Exception("Unable to find accountId: "+accountId);
		}
		
		Prepayment prepayment = prepaymentService.findByCustomerId(accountId);
		
		if(prepayment == null) {
			throw new Exception("Unable to find prepayment for accountId: "+accountId);
		}
		
		//account.setBalance(account.getBalance().add(amount).setScale(3, RoundingMode.HALF_UP));
		
		account.setBalance(amount);
		
		BigDecimal balance = account.getBalance();
		
		BigDecimal amountKwh = balance.multiply(new BigDecimal(prepayment.getRatePlan())).setScale(3, RoundingMode.HALF_UP);

		String serialNo = prepayment.getSerialNo();
		
		if(serialNo.length() > 11) {
			serialNo = serialNo.replaceAll("^CLE.?", "");
			serialNo = serialNo.replaceAll("0$", "");
		}
		//log.info("Serial no:"+serialNo);

		String token = tokenGeneratorService.generateToken(amountKwh, serialNo);
		
		account.setToken(token);
		
		if(prepayment.isMeterAMI()) {
			ActionLog actionLog = new ActionLog(prepayment.getSerialNo(), prepayment.getMeterPhone(), 
					prepayment.getCustomerPhone()+","+token, Constants.ActionName.TOKEN_GEN.name(),null,null);
			actionLogService.save(actionLog);
		}
		else {
			sendToken(prepayment.getSerialNo(), prepayment.getCustomerPhone(), token);
		}
		
		accountRepository.save(account);
		
		transactionService.save(new Transaction(amount, accountId, Constants.TransactionType.CREDIT.name(), token));
	}
	
	@Transactional
	@Override
	public Account withraw(BigDecimal amount, String accountId) throws Exception {
		if(amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
			throw new Exception("Debit amount must be > 0");
		}
		
		Account account = accountRepository.findByAccountId(accountId);
		
		if(account == null) {
			throw new Exception("Unable to find accountId: "+accountId);
		}
		
		account.setBalance(account.getBalance().subtract(amount).setScale(3, RoundingMode.HALF_UP));
		accountRepository.save(account);
		
		transactionService.save(new Transaction(amount, accountId, Constants.TransactionType.DEBIT.name(), account.getToken()));
		
		return account;
		
	}

	@Transactional(readOnly=true)
	@Override
	public Account balance(String accountId) throws Exception {
		Account account = accountRepository.findByAccountId(accountId);
		
		if(account == null) {
			throw new Exception("Unable to find accountId: "+accountId);
		}
		
		return new Account(account.getBalance());
	}

	private void sendToken(String serialNo, String customerPhone, String token) throws Exception {

		SMPPHelper smppHelper = new SMPPHelper("MONITOR", serialNo, customerPhone);

		String message = "PREPAYMENT TOKEN \n"
				+ "Walaaal, token kaan kushubo meter kaaga.\n"
				+ "token: "+token;

		if(!smppHelper.sendSMSWithRetry(message, Constants.UTILITY_NAME)) {
			throw new Exception("Unable to send token to customer phone: "+customerPhone);
		}

	}

}
