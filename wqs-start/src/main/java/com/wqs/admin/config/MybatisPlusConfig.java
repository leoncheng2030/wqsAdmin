package com.wqs.admin.config;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.reflection.MetaObject;
import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Configuration;
import org.noear.solon.annotation.Inject;

import javax.sql.DataSource;
import java.util.Date;

@Configuration
public class MybatisPlusConfig {

    @Bean("db1")
    public DataSource db1(@Inject("${wqs.db}") HikariDataSource ds) {
        return ds;
    }

    /**
     * 配置 MyBatis Plus 拦截器 (插件)
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 添加分页插件
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return interceptor;
    }

    /**
     * 配置 MyBatis Plus 自动填充处理器
     */
    @Bean
    public MetaObjectHandler metaObjectHandler() {
        return new MetaObjectHandler() {
            @Override
            public void insertFill(MetaObject metaObject) {
                // 填充创建时间
                this.strictInsertFill(metaObject, "createTime", Date.class, new Date());
                // 填充更新时间
                this.strictInsertFill(metaObject, "updateTime", Date.class, new Date());
                // 填充逻辑删除默认值 (0)
                this.strictInsertFill(metaObject, "deleted", Integer.class, 0);

                // 填充创建人/更新人 (如果已登录)
                try {
                    if (StpUtil.isLogin()) {
                        String userId = StpUtil.getLoginIdAsString();
                        this.strictInsertFill(metaObject, "createBy", String.class, userId);
                        this.strictInsertFill(metaObject, "updateBy", String.class, userId);
                    }
                } catch (Exception e) {
                    // 忽略未登录异常
                }
            }

            @Override
            public void updateFill(MetaObject metaObject) {
                // 填充更新时间
                this.strictUpdateFill(metaObject, "updateTime", Date.class, new Date());
                
                // 填充更新人
                try {
                    if (StpUtil.isLogin()) {
                        String userId = StpUtil.getLoginIdAsString();
                        this.strictUpdateFill(metaObject, "updateBy", String.class, userId);
                    }
                } catch (Exception e) {
                    // 忽略异常
                }
            }
        };
    }
}

