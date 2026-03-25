package com.bank.hexagon.infrastructure.service;

import com.bank.hexagon.domain.dto.AccountDTO;
import com.bank.hexagon.domain.entity.Account;
import com.bank.hexagon.domain.mapper.AccountMapper;
import com.bank.hexagon.infrastructure.AccountRepository;
import com.bank.hexagon.port.driver.AccountDriverPort;

import java.util.UUID;

/**
 * Orquestrador do hexágono - implementa AccountDriverPort
 * 
 * ⚠️ SEM @Service: é POJO puro registrado como @Bean em HexagonConfiguration
 * 
 * Responsabilidade:
 * 1. Receber AccountDTO da porta de entrada
 * 2. Converter AccountDTO → Account (acionando validações)
 * 3. Persistir Account validado via repository
 * 4. Retornar AccountDTO ao cliente
 */
public class AccountService implements AccountDriverPort {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public AccountService(AccountRepository accountRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    @Override
    public AccountDTO save(AccountDTO accountDTO) {
        Account account = accountMapper.toEntity(accountDTO);
        Account savedAccount = accountRepository.save(account);
        return accountMapper.toDTO(savedAccount);
    }

    @Override
    public AccountDTO update(AccountDTO accountDTO) {
        Account account = accountMapper.toEntity(accountDTO);

        Account existingAccount = accountRepository.findAccountById(account.getAccountId().id());
        if(existingAccount != null) {
            Account updatedAccount = accountRepository.update(account);
            return accountMapper.toDTO(updatedAccount);
        }
        throw new IllegalArgumentException("Account not found");
    }

    @Override
    public AccountDTO findAccountById(UUID id) {
        Account account = accountRepository.findAccountById(id);
        if(account != null) {
            return accountMapper.toDTO(account);
        }
        throw new IllegalArgumentException("Account not found");
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



