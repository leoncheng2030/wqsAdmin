package com.wqs.admin;

import org.noear.solon.Solon;
import org.noear.solon.annotation.SolonMain;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@SolonMain
public class App {
    public static void main(String[] args) {
        // 打印自定义 Banner
        printBanner();

        Solon.start(App.class, args, app -> {
            // 增加插件包扫描
            app.context().beanScan("com.wqs.plugin");
            
            // 初始化回调
            System.out.println("WQS Admin (Solon) started successfully!");
        });
    }

    private static void printBanner() {
        try (InputStream is = App.class.getResourceAsStream("/banner.txt")) {
            if (is != null) {
                String banner = new String(is.readAllBytes(), StandardCharsets.UTF_8);
                System.out.println(banner);
            }
        } catch (Exception e) {
            // 忽略 banner 读取错误
            System.out.println("WQS Admin");
        }
    }
}
