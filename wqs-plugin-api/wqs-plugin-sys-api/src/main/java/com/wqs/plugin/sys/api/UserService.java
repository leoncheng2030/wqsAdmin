package com.wqs.plugin.sys.api;

import com.wqs.plugin.sys.api.param.UserQueryRequest;
import com.wqs.plugin.sys.api.param.UserCreateRequest;

import java.util.List;

public interface UserService {
    List<UserDTO> list(UserQueryRequest req);
    UserDTO create(UserCreateRequest req);
}
