package com.wqs.plugin.sys.controller;

import com.wqs.core.common.Result;
import com.wqs.plugin.sys.api.UserDTO;
import com.wqs.plugin.sys.api.UserService;
import com.wqs.plugin.sys.api.param.UserQueryRequest;
import com.wqs.plugin.sys.api.param.UserCreateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.noear.solon.annotation.Body;
import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Inject;
import org.noear.solon.annotation.Mapping;

import java.util.List;

@Controller
@Tag(name = "用户接口", description = "用户查询与新增")
@Mapping("/sys/user")
public class UserController {

    @Inject
    UserService userService;

    @Mapping("list")
    @Operation(summary = "用户列表")
    public Result<List<UserDTO>> list(@Body UserQueryRequest req) {
        return Result.success(userService.list(req));
    }

    @Mapping("create")
    @Operation(summary = "新增用户")
    public Result<UserDTO> create(@Body UserCreateRequest req) {
        return Result.success(userService.create(req));
    }
}

