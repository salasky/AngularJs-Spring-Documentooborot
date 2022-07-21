package com.example.testproject1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * THIS IS MY MAIN CLASS
 *
 * @author smigranov
 */
@SpringBootApplication
@EnableCaching
public class DocumentFlow {
    public static void main(String[] args) {
        SpringApplication.run(DocumentFlow.class, args);
    }
}
