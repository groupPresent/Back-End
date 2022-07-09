package com.gift.present;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@EnableJpaAuditing
@SpringBootApplication
public class PresentApplication {

    @PostConstruct
    public void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
    }

    public static void main(String[] args) {
        SpringApplication
                .run(PresentApplication.class, args);
    }


//    public static void main(String[] args) {
//        //SpringApplication.run(PresentApplication.class, args);
//
//       new SpringApplicationBuilder(PresentApplication.class)
//               .properties(APPLICATION_LOCATIONS)
//               .run(args);
//
//    }



}



