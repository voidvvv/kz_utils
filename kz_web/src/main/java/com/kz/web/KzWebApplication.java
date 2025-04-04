package com.kz.web;

import com.kz.web.mapper.KzUserMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import java.util.Arrays;

@SpringBootApplication()
@EnableWebSecurity
@MapperScan("com.kz.web.mapper")
public class KzWebApplication {
	@Autowired
	private KzUserMapper kzUserMapper;

	public static void main(String[] args) throws ClassNotFoundException {
//		Class.forName("com.kz.web.config.secure.context.TokenAuthUtil");

		SpringApplication.run(KzWebApplication.class, args);
	}

}
