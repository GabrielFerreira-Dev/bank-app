package com.bank.hexagon.port.driven;

import com.bank.hexagon.domain.entity.Account;

import java.util.UUID;

public interface AccountDrivenPort {
    Account save(Account account);
    Account update(Account account);
    Account findAccountById(UUID id);
    void transfer(UUID fromAccountId, UUID toAccountId, Double amount);
    void credit(UUID id, Double amount);
    void debit(UUID id, Double amount);
    Double getBalance(UUID id);
}
