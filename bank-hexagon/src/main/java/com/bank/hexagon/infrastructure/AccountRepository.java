package com.bank.hexagon.infrastructure;

import com.bank.hexagon.domain.entity.Account;

import java.util.UUID;

/**
 * Interface interna do hexágono - trabalha com Account (entidade de domínio validada)
 * NÃO com DTO.
 * 
 * Responsabilidade: persistência de Account validados
 */
public interface AccountRepository {
    Account save(Account account);
    Account update(Account account);
    Account findAccountById(UUID id);
    void transfer(UUID fromAccountId, UUID toAccountId, Double amount);
    void credit(UUID id, Double amount);
    void debit(UUID id, Double amount);
    Double getBalance(UUID id);
}
