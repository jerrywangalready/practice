package com.practice.demo.test.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/**")
    public void transfer(HttpServletRequest request) {
        String path1 = request.getServletPath();
        System.out.println(path1);
    }

}
