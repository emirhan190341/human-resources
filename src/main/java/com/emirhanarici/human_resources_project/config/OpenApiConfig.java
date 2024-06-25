package com.emirhanarici.human_resources_project.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Emirhan Arici",
                        url = "https://github.com/emirhan190341/human-resources"
                ),
                description = "Human Resources Project " +
                        "(Spring Boot, Spring Security , PostgreSQL, Docker, Email Validation,JUnit, Integration Test,Prometheus, Grafana ) ",
                title = "Human Resources API",
                version = "1.0.0"
        )
)
public class OpenApiConfig {

}
