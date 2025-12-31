package com.wqs.admin.controller;

import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.annotation.Param;

@Controller
public class HelloController {

    @Mapping("/hello")
    public String hello(@Param(defaultValue = "World") String name) {
        return "Hello, " + name + "! Welcome to WQS Admin built with Solon.";
    }
}
