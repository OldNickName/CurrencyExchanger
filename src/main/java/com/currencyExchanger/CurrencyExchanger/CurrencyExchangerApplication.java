package com.currencyExchanger.CurrencyExchanger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.currencyExchanger.CurrencyExchanger.repository")
@EntityScan("com.currencyExchanger.CurrencyExchanger.model")
@SpringBootApplication
public class CurrencyExchangerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyExchangerApplication.class, args);
	}

}
