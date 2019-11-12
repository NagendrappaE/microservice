package com.ece.carservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
/*@EnableJpaRepositories(basePackages = "com.ece.carservice.repo")
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.ece")*/
public class CarserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarserviceApplication.class, args);
	}
	@Bean
	@LoadBalanced
   public RestTemplate restTemplate() {
		
		return new RestTemplate();

	}
	
	
}
