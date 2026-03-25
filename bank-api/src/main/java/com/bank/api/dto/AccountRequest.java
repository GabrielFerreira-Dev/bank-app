package com.bank.api.dto;

public record AccountRequest(
        String id,
        String bank,
        String branch,
        String accountNumber,
        Double balance,
        String accountHolderId,
        String limit,
        boolean active
) {
}
