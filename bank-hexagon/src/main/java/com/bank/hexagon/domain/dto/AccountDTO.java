package com.bank.hexagon.domain.dto;

import com.bank.hexagon.domain.vo.Limit;

import java.util.UUID;

public record AccountDTO(
    UUID id,
    String bank,
    String branch,
    String accountNumber,
    Double balance,
    UUID accountHolderId,
    String limit,
    boolean active
) {
}
