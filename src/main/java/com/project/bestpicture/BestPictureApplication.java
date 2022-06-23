package com.project.bestpicture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class BestPictureApplication {

    public static void main(String[] args) {
        SpringApplication.run(BestPictureApplication.class, args);
    }

}
