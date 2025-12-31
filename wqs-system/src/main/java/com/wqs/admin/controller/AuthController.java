package com.wqs.admin.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.wqs.core.common.Result;
import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.annotation.Param;

@Controller
@Mapping("/auth")
public class AuthController {

    @Mapping("login")
    public Result<String> login(@Param String username, @Param String password) {
        // 演示使用 SM3 加密密码比对 (假设数据库存储的是 SM3 加密后的密文)
        // String dbPassword = SmCryptoUtil.sm3("123456"); 
        
        // 模拟登录，实际需校验密码
        if ("admin".equals(username) && "123456".equals(password)) {
            StpUtil.login(10001); // 登录ID为 10001
            return Result.success("登录成功", StpUtil.getTokenValue());
        }
        return Result.failure("用户名或密码错误");
    }

    @Mapping("testSm")
    public Result<String> testSm() {
        String text = "Hello World";
        String sm3 = com.wqs.core.util.SmCryptoUtil.sm3(text);
        String sm4 = com.wqs.core.util.SmCryptoUtil.sm4Encrypt(text);
        String sm4Origin = com.wqs.core.util.SmCryptoUtil.sm4Decrypt(sm4);
        
        return Result.success("SM3: " + sm3 + " | SM4 Encrypt: " + sm4 + " | SM4 Decrypt: " + sm4Origin);
    }

    @Mapping("logout")
    public Result<String> logout() {
        StpUtil.logout();
        return Result.success("注销成功");
    }

    @Mapping("info")
    public Result<Object> info() {
        if(StpUtil.isLogin()) {
             return Result.success(StpUtil.getTokenInfo());
        }
        return Result.failure("未登录");
    }
}
