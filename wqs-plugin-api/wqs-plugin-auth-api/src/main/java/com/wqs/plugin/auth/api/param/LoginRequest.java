package com.wqs.plugin.auth.api.param;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "登录请求参数")
public class LoginRequest {
    @Schema(description = "用户名", required = true)
    public String username;
    @Schema(description = "密码", required = true)
    public String password;
}

