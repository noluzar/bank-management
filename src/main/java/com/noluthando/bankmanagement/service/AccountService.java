package com.noluthando.bankmanagement.service;

import com.noluthando.bankmanagement.model.Account;
import com.noluthando.bankmanagement.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    // ======================
    // Create / Save Account
    // ======================
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    // ======================
    // Get all accounts
    // ======================
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    // ======================
    // Find account by number
    // ======================
    public Account getAccountByNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
    }

    // ======================
    // Deposit
    // ======================
    public Account deposit(String accountNumber, BigDecimal amount) {
        Account account = getAccountByNumber(accountNumber);
        if (account == null) {
            throw new IllegalArgumentException("Account not found: " + accountNumber);
        }
        account.deposit(amount);
        return accountRepository.save(account);
    }

    // ======================
    // Withdraw
    // ======================
    public Account withdraw(String accountNumber, BigDecimal amount) {
        Account account = getAccountByNumber(accountNumber);
        if (account == null) {
            throw new IllegalArgumentException("Account not found: " + accountNumber);
        }
        account.withdraw(amount);
        return accountRepository.save(account);
    }

    // ======================
    // Transfer between accounts
    // ======================
    public void transfer(String fromAccountNumber, String toAccountNumber, BigDecimal amount) {
        Account fromAccount = getAccountByNumber(fromAccountNumber);
        Account toAccount = getAccountByNumber(toAccountNumber);

        if (fromAccount == null || toAccount == null) {
            throw new IllegalArgumentException("One or both accounts not found.");
        }

        fromAccount.withdraw(amount);
        toAccount.deposit(amount);

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
    }
}
