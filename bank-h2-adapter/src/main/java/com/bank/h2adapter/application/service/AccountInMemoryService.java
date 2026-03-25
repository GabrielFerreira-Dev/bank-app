package com.bank.h2adapter.application.service;

import com.bank.h2adapter.application.mapper.AccountMapper;
import com.bank.h2adapter.infrastructure.entity.AccountEntity;
import com.bank.h2adapter.infrastructure.repository.AccountJpaRepository;
import com.bank.hexagon.domain.entity.Account;
import com.bank.hexagon.port.driven.AccountDrivenPort;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class AccountInMemoryService implements AccountDrivenPort {

    private final AccountJpaRepository accountJpaRepository;
    private final AccountMapper accountMapper;

    @Override
    public Account save(Account account) {
        AccountEntity accountEntity = accountMapper.toEntity(account);
        AccountEntity savedEntity = accountJpaRepository.save(accountEntity);
        return accountMapper.toDomainEntity(savedEntity);
    }

    @Override
    public Account update(Account account) {
        AccountEntity accountEntity = accountMapper.toEntity(account);
        AccountEntity updatedEntity = accountJpaRepository.save(accountEntity);
        return accountMapper.toDomainEntity(updatedEntity);
    }

    @Override
    public Account findAccountById(UUID id) {
        return accountJpaRepository.findById(id)
                .map(accountMapper::toDomainEntity)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));
    }

    @Override
    @Transactional
    public void transfer(UUID fromAccountId, UUID toAccountId, Double amount) {
        // Recarregar ambas as contas do banco
        Account fromAccount = findAccountById(fromAccountId);
        Account toAccount = findAccountById(toAccountId);

        // Invocar lógica de domínio (métodos da entidade)
        fromAccount.debit(amount);
        toAccount.credit(amount);

        // Persistir ambas as contas modificadas
        save(fromAccount);
        save(toAccount);
    }

    @Override
    public void credit(UUID id, Double amount) {
        // Recarregar conta do banco
        Account account = findAccountById(id);

        // Invocar lógica de domínio
        account.credit(amount);

        // Persistir conta modificada
        save(account);
    }

    @Override
    public void debit(UUID id, Double amount) {
        // Recarregar conta do banco
        Account account = findAccountById(id);

        // Invocar lógica de domínio
        account.debit(amount);

        // Persistir conta modificada
        save(account);
    }

    @Override
    public Double getBalance(UUID id) {
        return accountJpaRepository.findById(id)
                .map(AccountEntity::getBalance)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));
    }
}
