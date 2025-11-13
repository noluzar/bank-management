package com.noluthando.bankmanagement.controller;

import com.noluthando.bankmanagement.model.Account;
import com.noluthando.bankmanagement.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    // ======================
    // Create a new account
    // ======================
    @PostMapping("/create")
    public Account createAccount(@RequestBody Account account) {
        return accountService.createAccount(account);
    }

    // ======================
    // Get all accounts
    // ======================
    @GetMapping("/all")
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    // ======================
    // Deposit money
    // ======================
    @PostMapping("/deposit")
    public Account deposit(@RequestParam String accountNumber, @RequestParam BigDecimal amount) {
        return accountService.deposit(accountNumber, amount);
    }

    // ======================
    // Withdraw money
    // ======================
    @PostMapping("/withdraw")
    public Account withdraw(@RequestParam String accountNumber, @RequestParam BigDecimal amount) {
        return accountService.withdraw(accountNumber, amount);
    }

    // ======================
    // Transfer money
    // ======================
    @PostMapping("/transfer")
    public String transfer(@RequestParam String fromAccount, @RequestParam String toAccount, @RequestParam BigDecimal amount) {
        accountService.transfer(fromAccount, toAccount, amount);
        return "Transfer successful!";
    }
}
