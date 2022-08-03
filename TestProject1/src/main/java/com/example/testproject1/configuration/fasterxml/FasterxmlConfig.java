package com.example.testproject1.configuration.fasterxml;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Класс для конфигурации ObjectMapper для FASTERXML
 *
 * @author smigranov
 */
@Configuration
public class FasterxmlConfig {

    /**
     * Метод получения сконфигурированного {@link ObjectMapper}
     *
     * @return возвращает объект класса {@link ObjectMapper}
     */
    @Bean
    public ObjectMapper getObjectMapper() {
        return new ObjectMapper().disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .enable(SerializationFeature.INDENT_OUTPUT);
    }
}
