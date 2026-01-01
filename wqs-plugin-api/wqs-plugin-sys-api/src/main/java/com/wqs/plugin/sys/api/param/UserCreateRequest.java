package com.wqs.plugin.sys.api.param;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "用户新增参数")
public class UserCreateRequest {
    @Schema(description = "用户名", required = true)
    public String username;
    @Schema(description = "密码", required = true)
    public String password;
    @Schema(description = "昵称")
    public String nickname;
    @Schema(description = "邮箱")
    public String email;
    @Schema(description = "手机号")
    public String mobile;
    @Schema(description = "状态 0:禁用 1:正常")
    public Integer status;
}

