package com.wqs.plugin.sys.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.annotation.Param;

@Controller
@Tag(name = "示例接口", description = "Hello 示例")
public class HelloController {

    @Mapping("/hello")
    @Operation(summary = "Hello")
    public String hello(@Param(defaultValue = "World") @Parameter(description = "名称") String name) {
        return "Hello, " + name + "! Welcome to WQS Admin built with Solon.";
    }
}
