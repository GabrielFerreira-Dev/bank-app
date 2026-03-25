package com.bank.hexagon;

import com.bank.hexagon.port.driven.AccountDrivenPort;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootTest(classes = ApplicationTests.TestApp.class)
class ApplicationTests {

	@SpringBootConfiguration
	@ComponentScan("com.bank.hexagon")
	static class TestApp {
	}

	@MockBean
	private AccountDrivenPort accountDrivenPort;

	@Test
	void contextLoads() {
	}

}
