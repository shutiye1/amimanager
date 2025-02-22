package safariami.manager.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import safariami.manager.model.Transaction;
import safariami.manager.repo.TransactionRepository;
import safariami.manager.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {
	
	@Autowired
	TransactionRepository transactionRepository;

	@Override
	public List<Transaction> findAll() {
		return transactionRepository.findAll();
	}

	@Override
	public Optional<Transaction> findById(Long id) {
		return transactionRepository.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		transactionRepository.deleteById(id);
	}

	@Override
	public void deleteAll() {
		transactionRepository.deleteAll();
	}

	@Override
	public Transaction save(Transaction transaction) {
		return transactionRepository.save(transaction);
	}

	@Override
	public Transaction findByAccountId(String accountId) {
		return transactionRepository.findByAccountId(accountId);
	}

	@Override
	public long countAll() {
		return transactionRepository.count();
	}

}
