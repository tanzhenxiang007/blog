package com.blog.玩玩.监听器.监听Servlet上下文对象.controller;

import com.blog.pojo.User2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/listener")
public class TestController4 {

    @GetMapping("/user")
    public User2 getUser(HttpServletRequest request) {
        ServletContext application = request.getServletContext();
        return (User2) application.getAttribute("user");
    }
}
