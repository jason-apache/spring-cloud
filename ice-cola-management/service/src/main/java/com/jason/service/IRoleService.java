package com.jason.service;

import com.jason.model.Role;
import com.jason.service.impl.RedisService;

import java.util.Map;

public interface IRoleService {

    Map<String,Object> selectAll(RedisService redisService);
    Map<String,Object> selectById(RedisService redisService,Long id);
    Map<String,Object> insertRole(RedisService redisService, Role role);
    Map<String,Object> delete(RedisService redisService,Long id);
    Map<String ,Object> update(RedisService redisService,Role role);
    Map<String,Object> insertOrUpdateRole(RedisService redisService,Role role);

}
