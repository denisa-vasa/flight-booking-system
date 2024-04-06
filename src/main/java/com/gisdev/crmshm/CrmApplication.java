package com.gisdev.crmshm;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "CRM-SHM API", version = "1.0"))
public class CrmApplication extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(CrmApplication.class, args);
	}
}
