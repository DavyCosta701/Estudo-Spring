package com.company.davyc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
    public class StudyApplication extends SpringBootServletInitializer {

    @GetMapping("/")
    public String startPage(){
        return "<h1> Start Page </h1>";
    }

        public static void main(String[] args) {
            SpringApplication.run(StudyApplication.class, args);
        }
    }
