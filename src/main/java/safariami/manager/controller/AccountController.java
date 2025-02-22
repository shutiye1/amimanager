package safariami.manager.controller;

import java.util.List;
import java.util.Optional;

import jakarta.validation.Valid;

import org.apache.logging.log4j.util.Strings;
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
import lombok.extern.slf4j.Slf4j;
import safariami.manager.model.Account;
import safariami.manager.service.AccountService;
import safariami.manager.util.CommonController;
import safariami.manager.util.error.CustomResponse;
import safariami.manager.util.error.ResourceNotFoundException;

@Slf4j
@RestController
public class AccountController extends CommonController {

    @Autowired
    AccountService accountService;

    @Operation(security = @SecurityRequirement(name = "apiKey"))
    @GetMapping(path="/accounts")
    public List<Account> getAll() {
        log.info("Listing accounts");
        return accountService.findAll();
    }

    @Operation(security = @SecurityRequirement(name = "apiKey"))
    @GetMapping(path="/account/{id}")
    public Account getOne(@PathVariable("id") Long id) {
        log.info("Listing account: " + id);
        return accountService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No such Account: " + id));
    }

    @Operation(security = @SecurityRequirement(name = "apiKey"))
    @PutMapping(path="/account/{id}")
    public Account updateOne(@Valid @RequestBody Account account, @PathVariable("id") Long id) {
        log.info("Update Account: " + id);
        Optional<Account> opAccount = accountService.findById(id);
        if (!opAccount.isPresent()) {
            return accountService.save(account);
        } else {
            account.setId(id);
            return accountService.save(account);
        }
    }

    @Operation(security = @SecurityRequirement(name = "apiKey"))
    @DeleteMapping(path="/account/{id}")
    public CustomResponse deleteOne(@PathVariable("id") Long id) {
        log.info("Deleting account: " + id);
        accountService.deleteById(id);
        return new CustomResponse("Account Deleted Successfully", HttpStatus.ACCEPTED.value());
    }

    @Operation(security = @SecurityRequirement(name = "apiKey"))
    @PostMapping(path="/account/create")
    public Account createOne(@Valid @RequestBody Account account) {
        log.info("Creating account");
        return accountService.save(account);
    }

    @Operation(security = @SecurityRequirement(name = "apiKey"))
    @PostMapping(path="/account/deposit")
    public CustomResponse deposit(@Valid @RequestBody Account account) {
        log.info("Deposit to account");

        if (account == null || Strings.isEmpty(account.getAccountId())) {
            return new CustomResponse("Invalid request", HttpStatus.BAD_REQUEST.value());
        }

        try {
            accountService.deposit(account.getBalance(), account.getAccountId());
        } catch (Exception e) {
            return new CustomResponse(e.getMessage(), HttpStatus.NOT_ACCEPTABLE.value());
        }

        return new CustomResponse("Deposit Successfully", HttpStatus.ACCEPTED.value());
    }

    @Operation(security = @SecurityRequirement(name = "apiKey"))
    @PostMapping(path="/account/withdraw")
    public CustomResponse withdraw(@Valid @RequestBody Account account) {
        log.info("Withdraw from account");

        if (account == null || Strings.isEmpty(account.getAccountId())) {
            return new CustomResponse("Invalid request", HttpStatus.BAD_REQUEST.value());
        }

        try {
            accountService.withraw(account.getBalance(), account.getAccountId());
        } catch (Exception e) {
            return new CustomResponse(e.getMessage(), HttpStatus.NOT_ACCEPTABLE.value());
        }

        return new CustomResponse("Withdrawn Successfully", HttpStatus.ACCEPTED.value());
    }

    @Operation(security = @SecurityRequirement(name = "apiKey"))
    @GetMapping(path="/account/balance/{accountId}")
    public Account balance(@PathVariable("accountId") String accountId) {
        log.info("Get account balance: " + accountId);

        if (Strings.isEmpty(accountId)) {
            throw new ResourceNotFoundException("Invalid request");
        }

        try {
            return accountService.balance(accountId);
        } catch (Exception e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }
}
