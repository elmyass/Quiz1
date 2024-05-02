package com.example.quiz1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.example.quiz1.repositories", "com.example.quiz1.entities"})
public class Quiz1Application {

    public static void main(String[] args) {
        SpringApplication.run(Quiz1Application.class, args);
    }

}
