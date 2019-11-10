package com.topchef.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;




@SpringBootApplication
public class TopChefApplication {

	public static void main(String[] args) {
		SpringApplication.run(TopChefApplication.class, args);
	}

}
