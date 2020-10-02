package com.codev.locatrash;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.codev.locatrash")
public class LocatrashApplication {

    public static void main(String[] args) {
        SpringApplication.run(LocatrashApplication.class, args);
    }

}
