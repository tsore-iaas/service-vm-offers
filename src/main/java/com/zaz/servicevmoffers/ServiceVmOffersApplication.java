package com.zaz.servicevmoffers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ServiceVmOffersApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceVmOffersApplication.class, args);
	}

}
