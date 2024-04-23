package com.app.blog;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Spring Boot Blog APP REST APIs",
				description = "Spring Boot Blog APP REST APIs Documentation",
				version = "v1.0",
				contact = @Contact(
						name = "Astream26",
						email = "avikr407@gmail.com",
						url = "https://github.com/Astream26"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://github.com/Astream26"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Spring Boot Blog APP REST APIs Documentation",
				url = "https://github.com/Astream26"
		)
)
public class SpringbootBlogAppRestApiApplication {

	@Bean
	BCryptPasswordEncoder getMethodEncoder() {
		return new BCryptPasswordEncoder();
	}
	public static void main(String[] args) {
		SpringApplication.run(SpringbootBlogAppRestApiApplication.class, args);
	}

}
