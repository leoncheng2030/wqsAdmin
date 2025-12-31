package com.wqs.admin.config;

import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Configuration;
import org.noear.solon.annotation.Inject;
import org.noear.solon.cache.jedis.RedisCacheService;
import org.noear.solon.data.cache.CacheService;

@Configuration
public class CacheConfig {

    @Bean
    public CacheService cacheService(@Inject("${wqs.redis}") RedisCacheService cache) {
        return cache;
    }
}
