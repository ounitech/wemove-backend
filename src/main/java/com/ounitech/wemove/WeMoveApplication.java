package com.ounitech.wemove;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WeMoveApplication {
    public static void main(String[] args) {
        SpringApplication.run(WeMoveApplication.class, args);
    }
}
