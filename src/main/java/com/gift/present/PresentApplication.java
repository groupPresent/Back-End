package com.gift.present;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class PresentApplication {

    public static void main(String[] args) {
        SpringApplication.run(PresentApplication.class, args);

       new SpringApplicationBuilder(PresentApplication.class)
               .properties(APPLICATION_LOCATIONS)
               .run(args);

    }

    public static final String APPLICATION_LOCATIONS = "spring.config.location="
            + "classpath:aws.yml";

}



