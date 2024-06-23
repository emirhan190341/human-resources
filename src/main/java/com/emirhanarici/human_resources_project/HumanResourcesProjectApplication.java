package com.emirhanarici.human_resources_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class HumanResourcesProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(HumanResourcesProjectApplication.class, args);
    }

}
