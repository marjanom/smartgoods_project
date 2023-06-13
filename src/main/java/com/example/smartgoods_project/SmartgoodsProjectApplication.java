package com.example.smartgoods_project;

import com.example.smartgoods_project.entity.repository.UserRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.PostConstruct;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Smartgoods Api", version = "2.1.0"))
public class SmartgoodsProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartgoodsProjectApplication.class, args);
	}

}
