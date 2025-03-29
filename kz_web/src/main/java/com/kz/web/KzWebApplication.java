package com.kz.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class KzWebApplication {

	public static void main(String[] args) throws ClassNotFoundException {
		Class.forName("com.kz.web.config.secure.context.TokenAuthUtil");
		SpringApplication.run(KzWebApplication.class, args);
	}

}
