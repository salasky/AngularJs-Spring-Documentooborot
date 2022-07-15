package com.example.testproject1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * THIS IS MY MAIN CLASS
 *
 * @author smigranov
 * @version 1.0
 */
@SpringBootApplication
public class TestProject1Application {
    public static void main(String[] args) {
        var context = SpringApplication.run(TestProject1Application.class, args);
    }

}
