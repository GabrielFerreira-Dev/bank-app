package com.bank.hexagon.infrastructure.repository;

import com.bank.hexagon.domain.entity.Account;
import com.bank.hexagon.infrastructure.AccountRepository;
import com.bank.hexagon.port.driven.AccountDrivenPort;

import java.util.UUID;

/**
 * Implementação da porta de persistência - trabalha com Account (domínio)
 * 
 * Responsabilidade: delegar Account validado para adapters via AccountDrivenPort
 * 
 * Nota: Removido @Repository - será instanciado via @Bean em HexagonConfiguration
 */
public class AccountRepositoryImpl implements AccountRepository {
    private final AccountDrivenPort accountDrivenPort;

    public AccountRepositoryImpl(AccountDrivenPort accountDrivenPort) {
        this.accountDrivenPort = accountDrivenPort;
    }

    @Override
    public Account save(Account account) {
        return accountDrivenPort.save(account);
    }

    @Override
    public Account update(Account account) {
        Account existingAccount = accountDrivenPort.findAccountById(account.getAccountId().id());
        if(existingAccount != null) {
            return accountDrivenPort.update(account);
        }
        throw new IllegalArgumentException("Account not found");
    }

    @Override
    public Account findAccountById(UUID id) {
        Account account = accountDrivenPort.findAccountById(id);
        if(account != null) {
            return account;
        }
        throw new IllegalArgumentException("Account not found");
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
