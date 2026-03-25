package com.bank.mysqladapter;

import com.bank.mysqladapter.infrastructure.repository.AccountJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootTest(classes = ApplicationTests.TestApp.class)
class ApplicationTests {

	@SpringBootConfiguration
	@ComponentScan("com.bank.mysqladapter")
	static class TestApp {
	}

	@MockBean
	private AccountJpaRepository accountJpaRepository;

	@Test
	void contextLoads() {
	}

}
