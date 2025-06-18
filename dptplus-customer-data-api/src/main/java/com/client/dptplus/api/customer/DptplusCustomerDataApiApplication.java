package com.client.dptplus.api.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class DptplusCustomerDataApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DptplusCustomerDataApiApplication.class, args);
	}

}
