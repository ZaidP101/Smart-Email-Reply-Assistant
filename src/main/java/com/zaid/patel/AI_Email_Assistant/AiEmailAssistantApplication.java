package com.zaid.patel.AI_Email_Assistant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class AiEmailAssistantApplication {

	public static void main(String[] args) {
		SpringApplication.run(AiEmailAssistantApplication.class, args);
	}

}
