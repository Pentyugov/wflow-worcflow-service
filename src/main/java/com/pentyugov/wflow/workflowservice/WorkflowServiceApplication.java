package com.pentyugov.wflow.workflowservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class WorkflowServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorkflowServiceApplication.class, args);
	}

}
