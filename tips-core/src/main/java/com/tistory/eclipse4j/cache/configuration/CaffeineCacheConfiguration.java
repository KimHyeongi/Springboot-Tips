package com.tistory.eclipse4j.cache.configuration;

import java.util.concurrent.TimeUnit;

import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.benmanes.caffeine.cache.Caffeine;

@Configuration
public class CaffeineCacheConfiguration {
    /**
     * https://programmer.group/caffeine-the-king-of-local-cache-performance.html
     * ex)
     * @Cacheable(cacheNames = "cachenames", cacheManager = "caffeineCacheManager")
     * public List<Object> getXxxxxxxxxx(Integer id) {
     */
    @Bean
    public CacheManager caffeineCacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager("cachenames");
        cacheManager.setCaffeine(Caffeine.newBuilder()
                .initialCapacity(100)
                .maximumSize(500)
                .expireAfterWrite(5, TimeUnit.MINUTES)
                .weakKeys()
                .recordStats());
        return cacheManager;
    }
}
