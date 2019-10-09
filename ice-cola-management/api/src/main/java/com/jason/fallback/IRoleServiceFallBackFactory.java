package com.jason.fallback;

import com.jason.model.Role;
import com.jason.service.IRoleService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class IRoleServiceFallBackFactory implements FallbackFactory<IRoleService> {
    private static Role role = new Role().setId(-1L).setName("熔断");
    private static Map<String, Object> map = new HashMap<>();
    private static List<Role> roleList = new ArrayList<>();
    private static Object[] a;

    public IRoleService create(Throwable throwable) {
        return new IRoleService() {
            @Override
            public Map<String, Object> selectAll() {
                return error();
            }

            @Override
            public Map<String, Object> selectById(Long id) {
                return error();
            }

            @Override
            public Map<String, Object> delete(Long id) {
                return error();
            }

            @Override
            public Map<String, Object> insertOrUpdateRole(Role role) {
                return error();
            }

            @Override
            public boolean clear() {
                return false;
            }

            private Map<String, Object> error(){
                if(roleList.isEmpty() || null == a){
                    roleList.add(role);
                    a = new Object[]{roleList};
                }
                map.put("result",a);
                return map;
            }
        };
    }
}
