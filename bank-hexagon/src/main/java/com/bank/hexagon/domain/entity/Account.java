package com.bank.hexagon.domain.entity;

import com.bank.hexagon.domain.exception.BankValidationException;
import com.bank.hexagon.domain.vo.Limit;

public class Account {
    private AccountId accountId;
    private final String bank;
    private final String branch;
    private final String accountNumber;
    private Double balance = 0.0;
    private final AccountHolderId accountHolderId;
    private Limit limits;
    private boolean active;

    public Account(String bank, String branch, String accountNumber, Double balance, AccountHolderId accountHolderId, Limit limits, boolean active) {
        validate(bank, branch, balance, accountNumber, limits);
        this.bank = bank;
        this.branch = branch;
        this.balance = balance;
        this.accountNumber = accountNumber;
        this.accountHolderId = accountHolderId;
        this.limits = limits;
        this.active = active;
    }

    private void validate(String bank, String branch, Double balance, String accountNumber, Limit limits) {
        StringBuilder sb = new StringBuilder();

        final int ACCOUNT_NUMBER_LENGHT = 8;
        final int BRANCH_NUMBER_LENGHT = 4;
        final int BANK_NUMBER_LENGHT = 3;

        if (accountNumber.length()  != ACCOUNT_NUMBER_LENGHT) {
            sb.append("Account number must be ").append(ACCOUNT_NUMBER_LENGHT).append(" digits long.");
        }
        if (branch.length() != BRANCH_NUMBER_LENGHT) {
            sb.append("Branch number must be ").append(BRANCH_NUMBER_LENGHT).append(" digits long.");
        }
        if (bank.length() != BANK_NUMBER_LENGHT) {
            sb.append("Bank number must be ").append(BANK_NUMBER_LENGHT).append(" digits long.");
        }
        if(balance < 0) {
            sb.append("Balance must be greater than 0.");
        }
        if (limits == null){
            sb.append("Limits cannot be null.");
        }

        BankValidationException.when(!sb.isEmpty(), sb.toString());
    }

    public void credit(Double amount) {
        this.balance += amount;
    }

    public void debit(Double amount) {
        this.balance -= amount;
    }

    public void transfer(Account destinationAccount, Double amount) {
        debit(amount);
        destinationAccount.credit(amount);
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public void setLimits(Limit limits) {
        this.limits = limits;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public AccountId getAccountId() {
        return accountId;
    }

    public String getBank() {
        return bank;
    }

    public String getBranch() {
        return branch;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Double getBalance() {
        return balance;
    }

    public AccountHolderId getAccountHolderId() {
        return accountHolderId;
    }

    public Limit getLimits() {
        return limits;
    }

    public boolean isActive() {
        return active;
    }
}
