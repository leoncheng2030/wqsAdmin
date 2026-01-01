package com.wqs.plugin.auth.service;

import cn.dev33.satoken.stp.StpUtil;
import com.wqs.plugin.auth.api.AuthService;
import com.wqs.plugin.auth.api.param.LoginRequest;
import org.noear.solon.annotation.Component;

@Component
public class AuthServiceImpl implements AuthService {
    @Override
    public boolean login(LoginRequest req) {
        if ("admin".equals(req.username) && "123456".equals(req.password)) {
            StpUtil.login(10001);
            return true;
        }
        return false;
    }

    @Override
    public void logout() {
        StpUtil.logout();
    }

    @Override
    public Object tokenInfo() {
        return StpUtil.getTokenInfo();
    }

    @Override
    public boolean isLogin() {
        return StpUtil.isLogin();
    }
}
