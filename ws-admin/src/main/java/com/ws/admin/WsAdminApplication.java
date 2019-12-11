package com.ws.admin;



import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EntityScan(basePackages={"com.ws.common.entity", "com.ws.admin.entity"})
@EnableJpaRepositories(basePackages={"com.ws.common.repository","com.ws.admin.repository"})
@ComponentScan(basePackages={"com.ws.common","com.ws.admin"})
@SpringBootApplication
public class WsAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(WsAdminApplication.class, args);
	}

}