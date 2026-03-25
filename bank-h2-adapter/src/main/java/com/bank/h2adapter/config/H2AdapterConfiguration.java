package com.bank.h2adapter.config;

import com.bank.h2adapter.application.mapper.AccountMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class H2AdapterConfiguration {

    @Bean
    public AccountMapper h2AccountMapper() {
        return new AccountMapper();
    }
}

