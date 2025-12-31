package com.wqs.admin.controller;

import com.wqs.core.common.Result;
import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Mapping;

@Controller
public class IndexController {

    @Mapping("/")
    public Result<String> index() {
        return Result.success("Welcome to WQS Admin System! (Powered by Solon)");
    }
}
