package com.bank.api.mapper;

import com.bank.api.dto.AccountRequest;
import com.bank.hexagon.domain.dto.AccountDTO;

import java.util.UUID;

public class AccountRequestMapper {
    
    public AccountDTO toDTO(AccountRequest request) {
        return new AccountDTO(
            request.id() == null ?  null : UUID.fromString(request.id()),
            request.bank(),
            request.branch(),
            request.accountNumber(),
            request.balance(),
            request.accountHolderId() == null ? null : UUID.fromString(request.accountHolderId()),
            request.limit(),
            true
        );
    }

    private String validateNoRequiredFields(String field) {
        if(field == null || field.isEmpty()) {
            return null;
        } else {
            return field;
        }
    }
}



