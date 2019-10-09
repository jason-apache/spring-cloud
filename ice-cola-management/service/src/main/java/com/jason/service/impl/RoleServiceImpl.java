package com.jason.service.impl;

import com.jason.mapper.RoleMapper;
import com.jason.model.Role;
import com.jason.service.IRoleService;
import com.jason.statusEnum.StatusEnum;
import com.jason.utils.JSONUtil;
import com.jason.utils.ResultMapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.RedisSystemException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;
    @Value("${redis_role_key}")
    private String key;

    private Map<String, Object> resultMap = new HashMap<String, Object>();

    public Map<String,Object> selectAll(RedisService redisService){
        resultMap.clear();
        List<Role> cache = toListHandel(redisService.get(key), Role.class);
        if(null == cache || cache.size()==0){
            List<Role> roleList = roleMapper.selectAll();

            if(isNotEmpty(roleList)){
                try {
                    redisService.set(key,JSONUtil.toJsonString(roleList));
                    ResultMapUtil.putResult(ResultMapUtil.putStatus(resultMap, StatusEnum.SUCCESS),roleList);
                }catch (RedisSystemException r){
                    redisService.set(key, JSONUtil.toJsonString(roleList));
                    ResultMapUtil.putResult(ResultMapUtil.putStatus(resultMap, StatusEnum.SUCCESS),roleList);
                }
            }else{
                ResultMapUtil.putStatus(resultMap, StatusEnum.MISSING.setMsg("mysql无数据"));
            }
        }else{
            System.out.println("select from redis*******************");
            List<Role> roleList = JSONUtil.toList(redisService.get(key), Role.class);
            ResultMapUtil.putResult(ResultMapUtil.putStatus(resultMap, StatusEnum.SUCCESS),roleList);
        }
        return resultMap;
    }

    public Map<String,Object> selectById(RedisService redisService,Long id){
        resultMap.clear();
        if(null == id || id<=0){
            return ResultMapUtil.putStatus(resultMap,StatusEnum.ERROR);
        }
        List<Role> cache = toListHandel(redisService.get(key), Role.class);
        if(null == cache || cache.size()==0){
            Role role = roleMapper.selectByPrimaryKey(id);
            if(null != role && !"".equals(role.getName())){
                ResultMapUtil.putStatus(resultMap,StatusEnum.SUCCESS);
                ResultMapUtil.putResult(resultMap,role);
            }else{
                ResultMapUtil.putStatus(resultMap,StatusEnum.MISSING.setMsg("mysql无数据"));
            }
        }else{
            for (Role r:cache){
                if(r.getId().equals(id)){
                    ResultMapUtil.putResult(ResultMapUtil.putStatus(resultMap,StatusEnum.SUCCESS),r);
                    return resultMap;
                }
            }
        }
        return resultMap;
    }

    public Map<String,Object> insertRole(RedisService redisService, Role role){
        resultMap.clear();
        int result = roleMapper.insertRoleUseId(role);
        if(result > 0){
            try {
                redisService.set(key,JSONUtil.toJsonString(role));
                ResultMapUtil.putStatus(resultMap,StatusEnum.SUCCESS);
            }catch (RedisSystemException r){
                redisService.set(key,JSONUtil.toJsonString(role));
                ResultMapUtil.putStatus(resultMap,StatusEnum.SUCCESS.setMsg("redis错误"));
            }
        }else{
            ResultMapUtil.putStatus(resultMap,StatusEnum.FAILED);
        }
        return resultMap;
    }

    public Map<String,Object> delete(RedisService redisService,Long id){
        resultMap.clear();
        int result = roleMapper.deleteByPrimaryKey(id);
        if(result > 0){
            try {
                List<Role> cache = JSONUtil.toList(redisService.get(key), Role.class);
                cache.removeIf(ele -> ele.getId() == id);
                redisService.set(key,JSONUtil.toJsonString(cache));
                ResultMapUtil.putStatus(resultMap,StatusEnum.SUCCESS);
            }catch (RedisSystemException r){
                List<Role> cache = JSONUtil.toList(redisService.get(key), Role.class);
                cache.removeIf(ele -> ele.getId() == id);
                redisService.set(key,JSONUtil.toJsonString(cache));
                ResultMapUtil.putStatus(resultMap,StatusEnum.SUCCESS.setMsg("redis错误"));
             }
        }else{
            ResultMapUtil.putStatus(resultMap,StatusEnum.FAILED);
        }
        return resultMap;
    }

    public Map<String ,Object> update(RedisService redisService,Role role){
        resultMap.clear();
        int result = roleMapper.updateByPrimaryKey(role);
        if(result > 0){
            try {
                System.out.println("update redis");
                List<Role> cache = JSONUtil.toList(redisService.get(key), Role.class);
                cache.removeIf(ele -> ele.getId()==role.getId());
                cache.add(role);
                redisService.set(key,JSONUtil.toJsonString(cache));
                ResultMapUtil.putStatus(resultMap,StatusEnum.SUCCESS);
            }catch (RedisSystemException r){
                List<Role> cache = JSONUtil.toList(redisService.get(key), Role.class);
                cache.removeIf(ele -> ele.getId()==role.getId());
                cache.add(role);
                redisService.set(key,JSONUtil.toJsonString(cache));
                ResultMapUtil.putStatus(resultMap,StatusEnum.SUCCESS.setMsg("redis错误"));
            }
        }else{
            ResultMapUtil.putStatus(resultMap,StatusEnum.FAILED);
        }
        return resultMap;
    }


    public Map<String,Object> insertOrUpdateRole(RedisService redisService,Role role){
        resultMap.clear();
        if(null == role){
            return ResultMapUtil.putStatus(resultMap,StatusEnum.ERROR);
        }

        if(null != role.getId() && role.getId()>0){
            return update(redisService,role);
        }else{
            return insertRole(redisService,role);
        }
    }

    private static boolean isNotEmpty(List<?> list){

        if(null == list || list.size()==0){
            return false;
        }
        return true;
    }

    /**
     *     缓存数据转化为List方法
     */
    private <T> List<T> toListHandel(String jsonData,Class<T> t){
        if(null !=jsonData && jsonData.length()>0){
            return JSONUtil.toList(jsonData,t);
        }
        return null;
    }

}
