package com.jason.service;

import com.jason.fallback.IRoleServiceFallBackFactory;
import com.jason.model.Role;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(value = "USER-PROVIDER",fallbackFactory = IRoleServiceFallBackFactory.class)
public interface IRoleService {

    @RequestMapping("/role/selectAll")
    Map<String,Object> selectAll();

    @RequestMapping("/role/selectById")
    Map<String,Object> selectById(@RequestParam(value = "id") Long id);

    @RequestMapping("/role/delete")
    Map<String,Object> delete(@RequestParam(value = "id") Long id);

    @RequestMapping("/role/insertOrUpdateRole")
    Map<String,Object> insertOrUpdateRole(@RequestBody Role role);

    @RequestMapping("/role/caCheClear")
    boolean clear();
}

