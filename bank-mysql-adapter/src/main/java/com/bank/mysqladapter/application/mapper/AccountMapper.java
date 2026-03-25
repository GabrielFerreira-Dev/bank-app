package com.bank.mysqladapter.application.mapper;

import com.bank.hexagon.domain.entity.Account;
import com.bank.hexagon.domain.entity.AccountHolderId;
import com.bank.hexagon.domain.vo.Limit;
import com.bank.mysqladapter.infrastructure.entity.AccountEntity;
import org.springframework.stereotype.Component;


@Component
public class AccountMapper {


    public AccountEntity toEntity(Account account) {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setId(account.getAccountId().id());
        accountEntity.setBank(account.getBank());
        accountEntity.setBranch(account.getBranch());
        accountEntity.setAccountNumber(account.getAccountNumber());
        accountEntity.setBalance(account.getBalance());
        accountEntity.setAccountHolderId(account.getAccountHolderId().id());
        accountEntity.setLimit(limitToStringArray(account.getLimits()));
        accountEntity.setActive(account.isActive());
        return accountEntity;
    }

    /**
     * Converte AccountEntity (JPA) → Account (domínio)
     * Reconstrói com validações via construtor de Account
     */
    public Account toDomainEntity(AccountEntity accountEntity) {
        Account account = new Account(
                accountEntity.getBank(),
                accountEntity.getBranch(),
                accountEntity.getAccountNumber(),
                accountEntity.getBalance(),
                new AccountHolderId(accountEntity.getAccountHolderId()),
                parseLimit(accountEntity.getLimit()),
                accountEntity.isActive()
        );
        account.setAccountId(new com.bank.hexagon.domain.entity.AccountId(accountEntity.getId()));
        return account;
    }

    /**
     * Converte string de limite em objeto Limit
     * Formato esperado: "limite1,limite2,limite3" (valores separados por vírgula)
     */
    private Limit parseLimit(String limitStr) {
        String[] limits = limitStr.split(",");
        if (limits.length != 3) {
            throw new IllegalArgumentException("Invalid limit format. Expected: 'limit1,limit2,limit3'");
        }
        return new Limit(
                Double.parseDouble(limits[0]),
                Double.parseDouble(limits[1]),
                Double.parseDouble(limits[2])
        );
    }

    private String limitToStringArray(Limit limit) {
        return limit.dailyTransactionLimit() + "," +
                limit.dailyTransferLimit() + "," +
                limit.dailyWithdrawalLimit();
    }
}
