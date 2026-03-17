package com.bank.h2adapter.application.service;

import com.bank.h2adapter.application.mapper.AccountMapper;
import com.bank.h2adapter.infrastructure.entity.AccountEntity;
import com.bank.h2adapter.infrastructure.repository.AccountJpaRepository;
import com.bank.hexagon.domain.dto.AccountDTO;
import com.bank.hexagon.infrastructure.service.AccountService;
import com.bank.hexagon.port.driven.AccountDrivenPort;
import com.bank.hexagon.port.driver.AccountDriverPort;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AccountInMemoryService implements AccountDrivenPort {

    private final AccountJpaRepository accountJpaRepository;
    private final AccountMapper accountMapper;

    @Override
    public AccountDTO save(AccountDTO accountDTO) {
        AccountEntity accountEntity = accountMapper.toEntity(accountDTO);
        return accountMapper.toDTO(accountJpaRepository.save(accountEntity));

    }

    @Override
    public AccountDTO update(AccountDTO accountDTO) {
        accountJpaRepository.findById(accountDTO.id())
                .ifPresentOrElse(entity -> {
                    BeanUtils.copyProperties(accountDTO, entity);
                    accountJpaRepository.save(entity);
                }, () -> {
                    throw new IllegalArgumentException("Account not found");
                });

        return accountDTO;
    }

    @Override
    public AccountDTO findAccountById(UUID id) {
        Optional<AccountEntity> accountEntity = accountJpaRepository.findById(id);
        if(accountEntity.isPresent()) {
            return accountMapper.toDTO(accountEntity.get());
        }
        throw new IllegalArgumentException("Account not found");
    }

    @Override
    @Transactional
    public void transfer(UUID fromAccountId, UUID toAccountId, Double amount) {
        accountJpaRepository.findById(fromAccountId)
                .ifPresentOrElse(entity -> {
                        entity.setBalance(entity.getBalance() - amount);
                        accountJpaRepository.save(entity);
                }, () -> {
                    throw new IllegalArgumentException("Account not found");
                });

        accountJpaRepository.findById(toAccountId)
                .ifPresentOrElse(entity -> {
                        entity.setBalance(entity.getBalance() + amount);
                        accountJpaRepository.save(entity);
                }, () -> {
                    throw new IllegalArgumentException("Account not found");
                });
    }

    @Override
    public void credit(UUID id, Double amount) {
        accountJpaRepository.findById(id)
                .ifPresentOrElse(entity -> {
                    entity.setBalance(entity.getBalance() + amount);
                    accountJpaRepository.save(entity);
                }, () -> {
                    throw new IllegalArgumentException("Is not possible to credit an account that was not found");
                });
    }

    @Override
    public void debit(UUID id, Double amount) {
        accountJpaRepository.findById(id)
                .ifPresentOrElse(entity -> {
                    entity.setBalance(entity.getBalance() - amount);
                    accountJpaRepository.save(entity);
                }, () -> {
                    throw new IllegalArgumentException("Is not possible to debit an account that was not found");
                });
    }

    @Override
    public Double getBalance(UUID id) {
        return accountJpaRepository.findById(id)
                .map(AccountEntity::getBalance)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));
    }
}
