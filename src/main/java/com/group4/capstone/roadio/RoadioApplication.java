package com.group4.capstone.roadio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan("com.group4.capstone.roadio.config")
public class RoadioApplication {

	public static void main(String[] args) {
		SpringApplication.run(RoadioApplication.class, args);
	}

}
