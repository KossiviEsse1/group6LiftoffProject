package org.launchcode.eventplanning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class EventPlanningApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventPlanningApplication.class, args);
	}

}
