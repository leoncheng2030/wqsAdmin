package com.wqs.admin.config;

import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Configuration;
import org.noear.solon.annotation.Inject;
import org.noear.solon.docs.DocDocket;
import org.noear.solon.docs.models.ApiInfo;
import com.github.xiaoymin.knife4j.solon.extension.OpenApiExtensionResolver;

@Configuration
public class DocConfig {

    @Inject
    OpenApiExtensionResolver openApiExtensionResolver;

    @Bean("adminApi")
    public DocDocket adminApi() {
        return new DocDocket()
                .groupName("管理后台接口")
                .info(new ApiInfo()
                        .title("WQS Admin 接口文档")
                        .description("基于 Solon + Knife4j 的接口文档")
                        .version("1.0")
                        .contact("WQS Team", "", ""))
                .schemes("http")
                .basicAuth(openApiExtensionResolver.getSetting().getBasic())
                .vendorExtensions(openApiExtensionResolver.buildExtensions())
                .apis("com.wqs.plugin.sys.controller");
    }

    @Bean("authApi")
    public DocDocket authApi() {
        return new DocDocket()
                .groupName("认证接口")
                .info(new ApiInfo().title("认证接口").version("1.0"))
                .schemes("http")
                .basicAuth(openApiExtensionResolver.getSetting().getBasic())
                .vendorExtensions(openApiExtensionResolver.buildExtensions())
                .apis("com.wqs.plugin.auth.controller");
    }
}
