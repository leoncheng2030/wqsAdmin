package com.wqs.plugin.sys.controller;

import com.wqs.core.common.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Mapping;

@Controller
@Tag(name = "首页接口", description = "系统欢迎页")
public class IndexController {

    @Mapping("/")
    @Operation(summary = "欢迎页")
    public Result<String> index() {
        return Result.success("Welcome to WQS Admin System! (Powered by Solon)");
    }
}
