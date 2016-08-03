package org.personal.mason.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by meidongxu on 6/22/15.
 */
@SpringBootApplication
@EnableScheduling
public class Application {

public static void main(String[] args) {
   SpringApplication.run(Application.class, args);
}
}
