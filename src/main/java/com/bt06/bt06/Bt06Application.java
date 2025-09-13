package com.bt06.bt06;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class Bt06Application {

    public static void main(String[] args) {
        SpringApplication.run(Bt06Application.class, args);
    }

}
