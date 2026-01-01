package com.wqs.plugin.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wqs.plugin.sys.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {}

