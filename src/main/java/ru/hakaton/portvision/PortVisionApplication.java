package ru.hakaton.portvision;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class PortVisionApplication {

    public static void main(String[] args) {
        SpringApplication.run(PortVisionApplication.class, args);
    }

}
