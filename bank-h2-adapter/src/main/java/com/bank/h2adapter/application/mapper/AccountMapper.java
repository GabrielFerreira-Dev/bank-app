package com.bank.h2adapter.application.mapper;

import com.bank.h2adapter.infrastructure.entity.AccountEntity;
import com.bank.hexagon.domain.dto.AccountDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountMapper {

    public AccountEntity toEntity(AccountDTO accountDTO) {
        AccountEntity accountEntity = new AccountEntity();
        BeanUtils.copyProperties(accountDTO, accountEntity);
        return accountEntity;
    }

    public AccountDTO toDTO(AccountEntity accountEntity) {
        return new AccountDTO(
                accountEntity.getId(),
                accountEntity.getBank(),
                accountEntity.getBranch(),
                accountEntity.getAccountNumber(),
                accountEntity.getBalance(),
                accountEntity.getAccountHolderId(),
                accountEntity.getLimit(),
                accountEntity.isActive()
        );
    }
}
