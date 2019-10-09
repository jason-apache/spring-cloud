package com.jason.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class turnController {

    @RequestMapping("/")
    public String turnIndexPage(){
        return "index";
    }
}
