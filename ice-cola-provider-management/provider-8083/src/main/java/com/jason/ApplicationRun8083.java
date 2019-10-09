package com.jason;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.jason.mapper")
@EnableDiscoveryClient
public class ApplicationRun8083 {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationRun8083.class,args);
    }
}
