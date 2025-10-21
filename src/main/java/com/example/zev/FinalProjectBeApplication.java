package com.example.zev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


@EnableJpaRepositories("com.example.zev.*")
@ComponentScan(basePackages = { "com.example.zev.*" })
@EntityScan("com.example.zev.*")
@SpringBootApplication
@EnableMethodSecurity
@EnableWebSecurity
@EnableJpaAuditing(auditorAwareRef = "auditAware")
public class FinalProjectBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinalProjectBeApplication.class, args);
    }

}
