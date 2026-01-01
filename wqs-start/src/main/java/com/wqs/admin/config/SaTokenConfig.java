package com.wqs.admin.config;

import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Configuration;
import org.noear.solon.core.handle.Filter;

@Configuration
public class SaTokenConfig {

    /**
     * 注册 Sa-Token 的全局过滤器
     */
    @Bean
    public Filter saTokenFilter() {
        return (ctx, chain) -> {
            SaRouter.match("/**")
                    .notMatch("/auth/login", "/auth/logout", "/", "/favicon.ico")
                    .notMatch("/doc.html", "/doc.html/**")
                    .notMatch("/webjars/**", "/img/**")
                    .notMatch("/swagger-resources", "/swagger-resources/**")
                    .notMatch("/swagger/v2", "/swagger/v2/**")
                    .check(r -> StpUtil.checkLogin());
            chain.doFilter(ctx);
        };
    }
}
