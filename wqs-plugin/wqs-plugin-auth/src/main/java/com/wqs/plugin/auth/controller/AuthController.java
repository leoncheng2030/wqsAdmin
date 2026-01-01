package com.wqs.plugin.auth.controller;

import com.wqs.core.common.Result;
import com.wqs.plugin.auth.api.AuthService;
import com.wqs.plugin.auth.api.param.LoginRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Inject;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.annotation.Body;

@Controller
@Tag(name = "认证接口", description = "登录、注销、令牌信息")
@Mapping("/auth")
public class AuthController {

    @Inject
    AuthService authService;

    @Mapping("login")
    @Operation(summary = "登录")
    public Result<String> login(@Body LoginRequest req) {
        boolean ok = authService.login(req);
        if (ok) {
            Object token = authService.tokenInfo();
            return Result.success("登录成功", String.valueOf(token));
        }
        return Result.failure("用户名或密码错误");
    }

    @Mapping("testSm")
    @Operation(summary = "国密算法测试")
    public Result<String> testSm() {
        String text = "Hello World";
        String sm3 = com.wqs.core.util.SmCryptoUtil.sm3(text);
        String sm4 = com.wqs.core.util.SmCryptoUtil.sm4Encrypt(text);
        String sm4Origin = com.wqs.core.util.SmCryptoUtil.sm4Decrypt(sm4);
        return Result.success("SM3: " + sm3 + " | SM4 Encrypt: " + sm4 + " | SM4 Decrypt: " + sm4Origin);
    }

    @Mapping("logout")
    @Operation(summary = "注销")
    public Result<String> logout() {
        authService.logout();
        return Result.success("注销成功");
    }

    @Mapping("info")
    @Operation(summary = "令牌信息")
    public Result<Object> info() {
        if(authService.isLogin()) {
             return Result.success(authService.tokenInfo());
        }
        return Result.failure("未登录");
    }
}
