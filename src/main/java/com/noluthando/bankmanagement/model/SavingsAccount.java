package com.noluthando.bankmanagement.model;

import jakarta.persistence.Entity;

import java.math.BigDecimal;

@Entity
public class SavingsAccount extends Account {

    private double interestRate; // e.g., 0.03 for 3% interest

    public SavingsAccount() {
        super();
    }

    public SavingsAccount(String accountNumber, String accountHolderName, BigDecimal balance, double interestRate) {
        super(accountNumber, accountHolderName, balance);
        this.interestRate = interestRate;
    }

    // =======================
    // Getters and Setters
    // =======================
    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    // Apply interest to balance
    public void applyInterest() {
        BigDecimal interest = getBalance().multiply(BigDecimal.valueOf(interestRate));
        setBalance(getBalance().add(interest));
    }
}
