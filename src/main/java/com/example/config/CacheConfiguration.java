package com.example.config;

import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.jsr107.Eh107Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.spi.CachingProvider;
import java.time.Duration;

@Configuration
public class CacheConfiguration {

    @Bean
    public CacheManager cacheManager(){
        CachingProvider provider = Caching.getCachingProvider();
        CacheManager cacheManager = provider.getCacheManager();
        var passengerCache = Eh107Configuration.fromEhcacheCacheConfiguration(
                CacheConfigurationBuilder.newCacheConfigurationBuilder(
                        Object.class,
                        Object.class,
                        ResourcePoolsBuilder.heap(1000)
                        )
                        .withExpiry(
                                ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofDays(1))
                        )
                        .build()
        );
        cacheManager.createCache(
                "com.example.database.entity.Passenger",
                passengerCache
        );
        return cacheManager;
    }
}
