package com.example.testproject1.configuration.cache;


import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * Класс конфигурации CaffeineConfig для кэширования
 *
 * @author smigranov
 */
@Configuration
public class CaffeineConfig {
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
     * initialCapacity-минимальный общий размер для внутренних хеш-таблиц
     * maximumSize-максимальное количество записей, которые может содержать кэш
     * expireAfterAccess-время автоматического удаления из кэша по истечении фиксированного времени после создания записи
     *
     * @return объект билдер для {@link Caffeine#}
     */
    Caffeine<Object, Object> caffeineCacheBuilder() {
        return Caffeine.newBuilder()
                .initialCapacity(100)
                .maximumSize(500)
                .expireAfterAccess(60, TimeUnit.MINUTES)
                .weakKeys()
                .recordStats();
    }
}