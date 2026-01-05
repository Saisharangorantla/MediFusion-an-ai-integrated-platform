package com.yourorg.telemedicine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableFeignClients
@SpringBootApplication
@EnableScheduling
public class WearableService1Application {

	public static void main(String[] args) {
		SpringApplication.run(WearableService1Application.class, args);
	}

}
