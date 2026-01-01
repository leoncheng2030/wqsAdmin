package com.wqs.plugin.sys.api.param;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "用户查询参数")
public class UserQueryRequest {
    @Schema(description = "用户名")
    public String username;
    @Schema(description = "状态 0:禁用 1:正常")
    public Integer status;
}

