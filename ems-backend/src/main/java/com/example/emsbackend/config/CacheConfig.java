package com.example.emsbackend.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Configuration;


//@Configuration
//@EnableCaching
public class CacheConfig {

    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
//        cacheManager.setCaches();
        return cacheManager;
    }
}
