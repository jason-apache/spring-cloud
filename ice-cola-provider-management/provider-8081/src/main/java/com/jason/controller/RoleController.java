package com.jason.controller;

import com.jason.model.Role;
import com.jason.service.impl.RedisService;
import com.jason.service.impl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleServiceImpl roleService;
    @Autowired
    private RedisService redisService;
    @Value("${redis_role_key}")
    private String key;

    @RequestMapping("/selectAll")
    public Map<String,Object> selectAll() throws Exception {
        if(true){
            throw new Exception("测试");
        }
        return roleService.selectAll(redisService);
    }

    @RequestMapping("/insertOrUpdateRole")
    public Map<String,Object> insertOrUpdateRole(@RequestBody Role role){
        return roleService.insertOrUpdateRole(redisService,role);
    }

    @RequestMapping("/delete")
    public Map<String,Object> delete(@RequestParam(value = "id") Long id){
        return roleService.delete(redisService,id);
    }

    @RequestMapping("/caCheClear")
    public boolean clear(){
        Long del = redisService.del(key);
        if(del>0){
            return true;
        }else{
            return false;
        }
    }

    @RequestMapping("/selectById")
    public Map<String ,Object> selectById(@RequestParam(value = "id") Long id){
        return roleService.selectById(redisService,id);
    }
}
