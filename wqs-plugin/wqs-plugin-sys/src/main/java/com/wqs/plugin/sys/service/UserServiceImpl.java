package com.wqs.plugin.sys.service;

import com.wqs.plugin.sys.api.UserDTO;
import com.wqs.plugin.sys.api.UserService;
import com.wqs.plugin.sys.api.param.UserQueryRequest;
import com.wqs.plugin.sys.api.param.UserCreateRequest;
import com.wqs.plugin.sys.entity.User;
import com.wqs.plugin.sys.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.noear.solon.annotation.Component;
import org.noear.solon.annotation.Inject;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserServiceImpl implements UserService {
    @Inject
    UserMapper userMapper;

    @Override
    public List<UserDTO> list(UserQueryRequest req) {
        QueryWrapper<User> qw = new QueryWrapper<>();
        if (req != null) {
            if (req.username != null && !req.username.isEmpty()) {
                qw.lambda().like(User::getUsername, req.username);
            }
            if (req.status != null) {
                qw.lambda().eq(User::getStatus, req.status);
            }
        }
        List<User> list = userMapper.selectList(qw);
        return list.stream().map(UserServiceImpl::toDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO create(UserCreateRequest req) {
        User u = new User();
        u.setUsername(req.username);
        u.setPassword(req.password);
        u.setNickname(req.nickname);
        u.setEmail(req.email);
        u.setMobile(req.mobile);
        u.setStatus(req.status != null ? req.status : 1);
        userMapper.insert(u);
        return toDTO(u);
    }

    // 可选的实体到DTO转换
    static UserDTO toDTO(User u) {
        UserDTO d = new UserDTO();
        d.id = u.getId();
        d.username = u.getUsername();
        d.nickname = u.getNickname();
        d.email = u.getEmail();
        d.mobile = u.getMobile();
        d.status = u.getStatus();
        return d;
    }
}

