package com.gftbank.dtb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MicroserviceDtbApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceDtbApplication.class, args);
	}

}
