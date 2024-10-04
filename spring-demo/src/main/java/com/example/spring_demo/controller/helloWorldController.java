package com.example.spring_demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class helloWorldController {
    @GetMapping("/hello")
    public String getsay(){
        return "hello";
    }
    @GetMapping("/hello/{name}")
    public String getHello(@PathVariable String name) {
        return ("hello " + name);
    }
}
