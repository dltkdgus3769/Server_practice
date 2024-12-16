package com.busanit501.boot_prac;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BootPracApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootPracApplication.class, args);
    }

}
