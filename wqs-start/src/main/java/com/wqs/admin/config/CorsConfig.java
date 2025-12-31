package com.wqs.admin.config;

import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Configuration;
import org.noear.solon.web.cors.CrossHandler;

@Configuration
public class CorsConfig {
    @Bean
    public CrossHandler crossHandler() {
        return new CrossHandler().allowedOrigins("*");
    }
}
