package com.marcinolek.mytimesheet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class MyTimesheetApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyTimesheetApplication.class, args);
	}

}
