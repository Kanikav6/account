package com.assignment.newaccount;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestNewaccountApplication {

	public static void main(String[] args) {
		SpringApplication.from(NewaccountApplication::main).with(TestNewaccountApplication.class).run(args);
	}

}
