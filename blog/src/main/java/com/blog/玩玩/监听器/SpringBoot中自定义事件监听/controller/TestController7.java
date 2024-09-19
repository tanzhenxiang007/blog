package com.blog.玩玩.监听器.SpringBoot中自定义事件监听.controller;

import com.blog.pojo.User2;
import com.blog.玩玩.监听器.SpringBoot中自定义事件监听.service.UserService2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/listener7")
@Slf4j
public class TestController7 {
    @Autowired
    private UserService2 userService;

    @GetMapping("/request")
    public String getRequestInfo(HttpServletRequest request, @RequestParam String name) {
        log.info("getRequestInfo请求进入");
        User2 user = new User2(1, name, "男");
        userService.getUser2(user);
//        System.out.println("requestListener中的初始化的name数据：" + request.getAttribute("name"));
        return "success";
    }

}