package com.jason.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

@Service
public class RedisService {

    @Autowired
    private JedisCluster jedisCluster;

    public String set(String key,String value){
        return jedisCluster.set(key,value);
    }

    public String get(String key){
        return jedisCluster.get(key);
    }

    public Long del(String key){
        return jedisCluster.del(key);
    }

    public Long expire(String key,Integer seconds){
        return jedisCluster.expire(key,seconds);
    }
}
