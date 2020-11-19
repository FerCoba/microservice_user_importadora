package com.microservice.user.importadora;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableEurekaClient
@EnableJpaRepositories
@EnableTransactionManagement
@SpringBootApplication
public class MicroserviceUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceUserApplication.class, args);
		PropertyConfigurator
				.configure(MicroserviceUserApplication.class.getClassLoader().getResource("log4j.properties"));
		
	}

}
