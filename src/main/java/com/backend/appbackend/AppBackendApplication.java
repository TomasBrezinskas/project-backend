package com.backend.appbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class AppBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppBackendApplication.class, args);
    }
}
