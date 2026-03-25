package com.bank.api.config;

import com.bank.api.mapper.AccountRequestMapper;
import com.bank.hexagon.domain.mapper.AccountMapper;
import com.bank.hexagon.infrastructure.AccountRepository;
import com.bank.hexagon.infrastructure.repository.AccountRepositoryImpl;
import com.bank.hexagon.infrastructure.service.AccountService;
import com.bank.hexagon.port.driven.AccountDrivenPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class HexagonConfiguration {

    @Bean
    public AccountRequestMapper accountRequestMapper() {
        return new AccountRequestMapper();
    }

    private AccountMapper createAccountMapper() {
        return new AccountMapper();
    }

    @Bean
    public AccountRepository accountRepository(AccountDrivenPort accountDrivenPort) {
        return new AccountRepositoryImpl(accountDrivenPort);
    }


    @Bean
    public AccountService accountService(AccountRepository accountRepository) {
        return new AccountService(accountRepository, createAccountMapper());
    }
}





