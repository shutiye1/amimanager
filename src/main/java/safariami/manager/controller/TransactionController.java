package safariami.manager.controller;

import java.util.List;
import java.util.Optional;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import safariami.manager.model.Transaction;
import safariami.manager.service.TransactionService;
import safariami.manager.util.CommonController;
import safariami.manager.util.error.CustomResponse;
import safariami.manager.util.error.ResourceNotFoundException;

@Slf4j
@RestController
public class TransactionController extends CommonController {
	
	@Autowired
	TransactionService transactionService;
	
	@Operation(summary = "Get all transactions", security = {@SecurityRequirement(name = "apiKey")})
	@GetMapping(path = "/transactions")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "List of all Transactions")
	})
	public List<Transaction> getAll() {
		log.info("Listing transactions");
		return transactionService.findAll();
	}
	
	@Operation(summary = "Get transaction by ID", security = {@SecurityRequirement(name = "apiKey")})
	@GetMapping(path = "/transaction/{id}")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Transaction retrieved successfully"),
		@ApiResponse(responseCode = "404", description = "Transaction not found")
	})
	public Transaction getOne(@PathVariable("id") Long id) {
		log.info("Listing transaction: " + id);
		return transactionService.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No such Transaction: " + id));
	}
	
	@Operation(summary = "Update transaction by ID", security = {@SecurityRequirement(name = "apiKey")})
	@PutMapping(path = "/transaction/{id}")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Transaction updated successfully"),
		@ApiResponse(responseCode = "404", description = "Transaction not found"),
		@ApiResponse(responseCode = "406", description = "Not Acceptable")
	})
	public Transaction updateOne(@Valid @RequestBody Transaction transaction, @PathVariable("id") Long id) {
		log.info("Update Transaction: " + id);
		Optional<Transaction> opTransaction = transactionService.findById(id);
		if (!opTransaction.isPresent()) {
			return transactionService.save(transaction);
		} else {
			transaction.setId(id);
			return transactionService.save(transaction);
		}
	}
	
	@Operation(summary = "Delete transaction by ID", security = {@SecurityRequirement(name = "apiKey")})
	@DeleteMapping(path = "/transaction/{id}")
	@ApiResponses({
		@ApiResponse(responseCode = "202", description = "Transaction deleted successfully"),
		@ApiResponse(responseCode = "404", description = "Transaction not found"),
		@ApiResponse(responseCode = "406", description = "Not Acceptable")
	})
	public CustomResponse deleteOne(@PathVariable("id") Long id) {
		log.info("Deleting transaction: " + id);
		transactionService.deleteById(id);
		return new CustomResponse("Transaction Deleted Successfully", HttpStatus.ACCEPTED.value());
	}
	
	@Operation(summary = "Create a new transaction", security = {@SecurityRequirement(name = "apiKey")})
	@PostMapping(path = "/transaction/create")
	@ApiResponses({
		@ApiResponse(responseCode = "201", description = "Transaction created successfully"),
		@ApiResponse(responseCode = "406", description = "Not Acceptable")
	})
	public Transaction createOne(@Valid @RequestBody Transaction transaction) {
		log.info("Creating transaction");
		return transactionService.save(transaction);
	}
}
