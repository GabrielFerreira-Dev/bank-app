package com.bank.h2adapter;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

@SpringBootTest(classes = ApplicationTests.TestApp.class)
class ApplicationTests {

	@SpringBootConfiguration
	@ComponentScan("com.bank.h2adapter")
	static class TestApp {
	}

	@Test
	void contextLoads() {
	}

}
