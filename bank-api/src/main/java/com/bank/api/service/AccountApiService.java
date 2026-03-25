package com.bank.api.service;

import com.bank.hexagon.domain.dto.AccountDTO;

import java.util.UUID;

public interface AccountApiService {
    AccountDTO save(AccountDTO accountDTO);
    AccountDTO update(AccountDTO accountDTO);
    AccountDTO findAccountById(UUID id);
    void transfer(UUID fromAccountId, UUID toAccountId, Double amount);
    void credit(UUID id, Double amount);
    void debit(UUID id, Double amount);
    Double getBalance(UUID id);
}
