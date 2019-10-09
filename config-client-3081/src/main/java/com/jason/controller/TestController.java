package com.jason.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Value("${mybatis.type-aliases-package}")
    private String mybatis;

    @RequestMapping("/")
    public String back(){
        return mybatis;
    }
}
