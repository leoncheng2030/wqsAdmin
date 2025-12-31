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
            // 指定拦截路径
            SaRouter.match("/**")
                    // 排除登录接口和静态资源
                    .notMatch("/auth/login", "/auth/logout", "/", "/favicon.ico")
                    .check(r -> StpUtil.checkLogin());
            
            chain.doFilter(ctx);
        };
    }
}
