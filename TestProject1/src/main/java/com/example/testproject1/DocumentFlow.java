package com.example.testproject1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * THIS IS MY MAIN CLASS
 *
 * @author smigranov
 */
@SpringBootApplication
public class DocumentFlow {
    public static void main(String[] args) {
        var context = SpringApplication.run(DocumentFlow.class, args);
    }
}
