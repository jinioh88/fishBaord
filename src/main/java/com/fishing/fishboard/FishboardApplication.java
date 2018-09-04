package com.fishing.fishboard;

import com.fishing.fishboard.listener.StartListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FishboardApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(FishboardApplication.class);
        application.addListeners(new StartListener());
        application.run(args);
    }
}
