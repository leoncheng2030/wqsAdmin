package com.wqs.admin.handler;

import com.wqs.core.common.Result;
import com.wqs.core.exception.BusinessException;
import org.noear.solon.annotation.Component;
import org.noear.solon.core.handle.Context;
import org.noear.solon.core.handle.Filter;
import org.noear.solon.core.handle.FilterChain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 全局异常处理过滤器
 * Solon 的全局异常处理通常通过 Filter 或者 @Catch 实现。
 * 这里使用 Filter 可以在最外层捕获异常并输出 JSON。
 */
@Component
public class GlobalExceptionHandler implements Filter {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Override
    public void doFilter(Context ctx, FilterChain chain) throws Throwable {
        try {
            chain.doFilter(ctx);
        } catch (Throwable e) {
            log.error("Global Exception: ", e);
            
            Result<?> result;
            if (e instanceof BusinessException) {
                BusinessException be = (BusinessException) e;
                result = Result.failure(be.getCode(), be.getMessage());
            } else {
                result = Result.failure("系统内部错误: " + e.getMessage());
            }

            ctx.render(result);
        }
    }
}
