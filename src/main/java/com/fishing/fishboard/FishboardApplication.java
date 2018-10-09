package com.fishing.fishboard;

import com.fishing.fishboard.listener.StartListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class FishboardApplication {

    public static final String APPLICATION_LOCATIONS = "spring.config.location="
            + "classpath:application.yml,"
            + "classpath:aws.yml";

    public static void main(String[] args) {
        new SpringApplicationBuilder(FishboardApplication.class)
                .listeners(new StartListener())
                .properties(APPLICATION_LOCATIONS)
                .run(args);
    }
}
