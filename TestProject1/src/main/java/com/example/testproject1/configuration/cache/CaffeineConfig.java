package com.example.testproject1.configuration.cache;


import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * Класс конфигурации CaffeineConfig для кэширования данных для получения
 * объектов клаасса Person, Department, Organization
 *
 * @author smigranov
 */
@Configuration
public class CaffeineConfig {
    /**
     * Минимальный общий размер для внутренних хеш-таблиц
     */
    private static final int INITAL_CAPACITY = 100;
    /**
     * Максимальное количество записей, которые может содержать кэш
     */
    private static final int MAX_SIZE = 500;
    /**
     * Время автоматического удаления из кэша по истечении фиксированного времени после создания записи
     */
    private static final int EXPIRE_TIME = 60;

    /**
     * Бин CaffeineCacheManager вместо стандартного CacheManager
     *
     * @return бин {@link CaffeineCacheManager} с настройками параметров кэша
     */
    @Bean
    public CaffeineCacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager("person", "department", "organization");
        cacheManager.setCaffeine(caffeineCacheBuilder());
        return cacheManager;
    }

    /**
     * Метод для настройки кэша
     *
     * @return объект билдер для {@link Caffeine#}
     */
    private Caffeine<Object, Object> caffeineCacheBuilder() {
        return Caffeine.newBuilder()
                .initialCapacity(INITAL_CAPACITY)
                .maximumSize(MAX_SIZE)
                .expireAfterAccess(EXPIRE_TIME, TimeUnit.MINUTES)
                .weakKeys()
                .recordStats();
    }
}