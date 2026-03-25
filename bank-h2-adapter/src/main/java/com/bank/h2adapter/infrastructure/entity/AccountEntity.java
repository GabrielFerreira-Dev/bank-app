package com.bank.h2adapter.infrastructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "account")
public class AccountEntity {

    @Id
    private UUID id;

    @NotNull(message = "Bank cannot be null")
    private String bank;

    @NotNull(message = "Branch cannot be null")
    private String branch;

    @NotNull(message = "Account number cannot be null")
    private String accountNumber;

    private Double balance;

    @NotNull(message = "Account holder id cannot be null")
    @Column(name = "account_holder_id")
    private UUID accountHolderId;

    @NotNull(message = "Limit cannot be null")
    @Column(name = "account_limit")
    private String limit;

    @Column(name = "is_active", nullable = false)
    private boolean active;



}
