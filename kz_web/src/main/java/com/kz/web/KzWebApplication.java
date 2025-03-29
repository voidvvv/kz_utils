package com.kz.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableWebSecurity
public class KzWebApplication {

	public static void main(String[] args) throws ClassNotFoundException {
		Class.forName("com.kz.web.config.secure.context.TokenAuthUtil");
		SpringApplication.run(KzWebApplication.class, args);
	}

}
