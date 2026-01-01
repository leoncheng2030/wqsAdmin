package com.wqs.plugin.auth.api;

import com.wqs.plugin.auth.api.param.LoginRequest;

public interface AuthService {
    boolean login(LoginRequest req);
    void logout();
    Object tokenInfo();
    boolean isLogin();
}

