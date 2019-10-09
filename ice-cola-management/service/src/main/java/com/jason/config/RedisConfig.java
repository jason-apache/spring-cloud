package com.jason.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class RedisConfig {

    @Autowired
    private RedisProperties redisProperties;

    @Bean
    public JedisCluster getJedisCluster(){
        String nodes =redisProperties.getNodes();

        String[] nodesArray = nodes.split(",");
        Set<HostAndPort> hostAndPorts = new HashSet<HostAndPort>();
        for (String node : nodesArray){
            String[] hostAndPortArray = node.split(":");
            HostAndPort hostAndPort = new HostAndPort(hostAndPortArray[0], Integer.parseInt(hostAndPortArray[1]));
            hostAndPorts.add(hostAndPort);
        }
        JedisCluster jedisCluster = new JedisCluster(hostAndPorts, redisProperties.getCommandTimeout(), redisProperties.getMaxAttempts());
        return jedisCluster;
    }
}
