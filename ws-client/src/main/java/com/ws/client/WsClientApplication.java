package com.ws.client;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EntityScan(basePackages={"com.ws.common.entity", "com.ws.client.entity"})
@EnableJpaRepositories(basePackages={"com.ws.common.repository","com.ws.client.repository"})
@ComponentScan(basePackages={"com.ws.common","com.ws.client"})
@SpringBootApplication
public class WsClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(WsClientApplication.class, args);
	}

}