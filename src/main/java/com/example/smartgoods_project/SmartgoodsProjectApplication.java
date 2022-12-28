package com.example.smartgoods_project;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "User Management API", version = "0.1"))
public class SmartgoodsProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartgoodsProjectApplication.class, args);
	}

}
