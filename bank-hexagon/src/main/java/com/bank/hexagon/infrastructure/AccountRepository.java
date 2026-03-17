package com.bank.hexagon.infrastructure;

import com.bank.hexagon.domain.dto.AccountDTO;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountRepository {
    AccountDTO save(AccountDTO accountDTO);
    AccountDTO update(AccountDTO accountDTO);
    AccountDTO findAccountById(UUID id);
    void transfer(UUID fromAccountId, UUID toAccountId, Double amount);
    void credit(UUID id, Double amount);
    void debit(UUID id, Double amount);
    Double getBalance(UUID id);
}
