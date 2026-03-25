package com.bank.api.application.controller;

import com.bank.api.dto.AccountRequest;
import com.bank.api.mapper.AccountRequestMapper;
import com.bank.api.service.AccountApiService;
import com.bank.hexagon.domain.dto.AccountDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("account")
public class AccountController {
    private final AccountApiService accountApiService;
    private final AccountRequestMapper accountRequestMapper;

    public AccountController(AccountApiService accountApiService, AccountRequestMapper accountRequestMapper) {
        this.accountApiService = accountApiService;
        this.accountRequestMapper = accountRequestMapper;
    }

    @GetMapping
    public ResponseEntity<?> findAccountById(@RequestParam(name = "id") UUID id) {
        return ResponseEntity.ok(accountApiService.findAccountById(id));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody AccountRequest request) {
        AccountDTO accountDTO = accountRequestMapper.toDTO(request);
        return ResponseEntity.ok(accountApiService.save(accountDTO));
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody AccountRequest request) {
        AccountDTO accountDTO = accountRequestMapper.toDTO(request);
        return ResponseEntity.ok(accountApiService.update(accountDTO));
    }

    @PostMapping("/transfer")
    public ResponseEntity<?> transfer(@RequestParam(name = "from") UUID fromAccountId,
                                                              @RequestParam(name = "to") UUID toAccountId,
                                                              @RequestParam(name = "amount") Double amount) {
        accountApiService.transfer(fromAccountId, toAccountId, amount);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/credit")
    public ResponseEntity<?> credit(@RequestParam(name = "id") UUID id, @RequestParam(name = "amount") Double amount) {
        accountApiService.credit(id, amount);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/debit")
    public ResponseEntity<?> debit(@RequestParam(name = "id") UUID id, @RequestParam(name = "amount") Double amount) {
        accountApiService.debit(id, amount);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/balance")
    public ResponseEntity<?> getBalance(@RequestParam(name = "id") UUID id) {
        return ResponseEntity.ok(accountApiService.getBalance(id));
    }
}
