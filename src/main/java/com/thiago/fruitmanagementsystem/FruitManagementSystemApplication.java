package com.thiago.fruitmanagementsystem;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;

import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
        info = @Info(
                title = "API REST",
                description = "API REST",
                version = "2.0.0",
                license = @License(name = "Apache 2.0", url = "http://springdoc.org")
        )
)
@SpringBootApplication
public class FruitManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(FruitManagementSystemApplication.class, args);
    }

}
