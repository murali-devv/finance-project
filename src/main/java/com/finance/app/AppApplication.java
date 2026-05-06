package com.finance.app;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "FINANCE SERVICE",
                description = "Finance management project with CRUD Operations",
                version = "1.0.0",
                contact = @Contact(
                        name = "Murali",
                        email = "murali@gmail.com",
                        url = "finance.com"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url ="finance.com"

                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "Additional documentation for finance management projec"
        )
)
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

}
