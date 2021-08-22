package com.blank.epicfserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EpicfServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EpicfServerApplication.class, args);
	}

}
