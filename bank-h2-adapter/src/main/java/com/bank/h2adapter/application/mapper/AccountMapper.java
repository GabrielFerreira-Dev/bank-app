package com.bank.h2adapter.application.mapper;

import com.bank.h2adapter.infrastructure.entity.AccountEntity;
import com.bank.hexagon.domain.entity.Account;
import com.bank.hexagon.domain.entity.AccountHolderId;
import com.bank.hexagon.domain.vo.Limit;
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
        accountEntity.setLimit(account.getLimits().toString());
        accountEntity.setActive(account.isActive());
        return accountEntity;
    }


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
}
