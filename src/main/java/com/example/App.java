package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
//@EnableConfigurationProperties(value = {DB2Config.class, DB1Config.class})
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}
