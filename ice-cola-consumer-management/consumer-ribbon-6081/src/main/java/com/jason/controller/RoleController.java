package com.jason.controller;

import com.jason.service.IRoleService;
import com.jason.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    public IRoleService iRoleService;

    @RequestMapping("/selectAll")
    public Map<String,Object> selectAll(){
        return iRoleService.selectAll();
    }

    @RequestMapping("/insertOrUpdateRole")
    public Map<String,Object> insertOrUpdateRole(Role role){
        return iRoleService.insertOrUpdateRole(role);
    }

    @RequestMapping("/delete")
    public Map<String,Object> delete(Long id){
        return iRoleService.delete(id);
    }

    @RequestMapping("/caCheClear")
    public boolean clear(){
        return iRoleService.clear();
    }

    @RequestMapping("/selectById")
    public Map<String ,Object> selectById(Long id){
        return iRoleService.selectById(id);
    }
}
