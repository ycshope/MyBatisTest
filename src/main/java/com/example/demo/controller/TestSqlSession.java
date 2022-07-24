package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestSqlSession {
    @RequestMapping("/testSqlSession")
    public String testSqlSession() {

        return "success";
    }
}
