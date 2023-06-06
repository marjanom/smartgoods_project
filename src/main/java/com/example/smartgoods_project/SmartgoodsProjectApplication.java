package com.example.smartgoods_project;

import com.example.smartgoods_project.entity.repository.UserRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.PostConstruct;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "User Management API", version = "0.1"))
public class SmartgoodsProjectApplication {

	@Autowired
	UserRepository userRepository;
	@PostConstruct
	private void initializer(){
	userRepository.maker();
	}
	public static void main(String[] args) {
		SpringApplication.run(SmartgoodsProjectApplication.class, args);
	}

}
