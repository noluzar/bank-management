package com.noluthando.bankmanagement.model;

import jakarta.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class CheckingAccount extends Account {

    private BigDecimal overdraftLimit = BigDecimal.ZERO;

    public CheckingAccount() {
        super();
    }

    public CheckingAccount(String accountNumber, String accountHolderName, BigDecimal balance, BigDecimal overdraftLimit) {
        super(accountNumber, accountHolderName, balance);
        this.overdraftLimit = overdraftLimit;
    }

    // =======================
    // Getters and Setters
    // =======================
    public BigDecimal getOverdraftLimit() {
        return overdraftLimit;
    }

    public void setOverdraftLimit(BigDecimal overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void withdraw(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive.");
        }
        BigDecimal available = getBalance().add(overdraftLimit);
        if (amount.compareTo(available) > 0) {
            throw new IllegalArgumentException("Insufficient funds including overdraft limit.");
        }
        setBalance(getBalance().subtract(amount));
    }
}
