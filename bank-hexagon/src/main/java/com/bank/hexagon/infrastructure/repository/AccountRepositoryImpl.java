package com.bank.hexagon.infrastructure.repository;

import com.bank.hexagon.domain.dto.AccountDTO;
import com.bank.hexagon.infrastructure.AccountRepository;
import com.bank.hexagon.port.driven.AccountDrivenPort;

import java.util.UUID;

public class AccountRepositoryImpl implements AccountRepository {
    private final AccountDrivenPort accountDrivenPort;

    public AccountRepositoryImpl(AccountDrivenPort accountDrivenPort) {
        this.accountDrivenPort = accountDrivenPort;
    }

    @Override
    public AccountDTO save(AccountDTO accountDTO) {
        return accountDrivenPort.save(accountDTO);
    }

    @Override
    public AccountDTO update(AccountDTO accountDTO) {
        AccountDTO account = accountDrivenPort.findAccountById(accountDTO.id());
        if(account != null) {
            return accountDrivenPort.update(account);
        }
        throw  new IllegalArgumentException("Account not found");
    }

    @Override
    public AccountDTO findAccountById(UUID id) {
        AccountDTO account = accountDrivenPort.findAccountById(id);
        if(account != null) {
            return account;
        }
        throw  new IllegalArgumentException("Account not found");
    }

    @Override
    public void transfer(UUID fromAccountId, UUID toAccountId, Double amount) {
        accountDrivenPort.transfer(fromAccountId, toAccountId, amount);
    }

    @Override
    public void credit(UUID id, Double amount) {
        accountDrivenPort.credit(id, amount);
    }

    @Override
    public void debit(UUID id, Double amount) {
        accountDrivenPort.debit(id, amount);
    }

    @Override
    public Double getBalance(UUID id) {
        return accountDrivenPort.getBalance(id);
    }
}
