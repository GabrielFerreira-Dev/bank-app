package com.bank.hexagon.infrastructure.service;

import com.bank.hexagon.domain.dto.AccountDTO;
import com.bank.hexagon.infrastructure.AccountRepository;
import com.bank.hexagon.port.driver.AccountDriverPort;

import java.util.UUID;

public class AccountService implements AccountDriverPort {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDTO save(AccountDTO accountDTO) {
        return accountRepository.save(accountDTO);
    }

    @Override
    public AccountDTO update(AccountDTO accountDTO) {
        AccountDTO account = accountRepository.findAccountById(accountDTO.id());
        if(account != null) {
            return accountRepository.update(account);
        }
        throw  new IllegalArgumentException("Account not found");
    }

    @Override
    public AccountDTO findAccountById(UUID id) {
        AccountDTO account = accountRepository.findAccountById(id);
        if(account != null) {
            return account;
        }
        throw  new IllegalArgumentException("Account not found");
    }

    @Override
    public void transfer(UUID fromAccountId, UUID toAccountId, Double amount) {
            accountRepository.transfer(fromAccountId, toAccountId, amount);
    }

    @Override
    public void credit(UUID id, Double amount) {
        accountRepository.credit(id, amount);
    }

    @Override
    public void debit(UUID id, Double amount) {
        accountRepository.debit(id, amount);
    }

    @Override
    public Double getBalance(UUID id) {
        return accountRepository.getBalance(id);
    }
}
