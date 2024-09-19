package com.blog.玩玩.监听器.监听客户端请求ServletRequest对象.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/listener6")
@Slf4j
public class TestController6 {

    @GetMapping("/request")
    public String getRequestInfo(HttpServletRequest request, @RequestParam String name) {
        log.info("第二步servletRequest请求到这里");
        //请求先被ServletRequestListener监听器拦截 然后再到这里 然后还能回到ServletRequestListener监听器结束那块逻辑
        System.out.println("requestListener中的初始化的name数据：" + request.getAttribute("name"));
        return "success";
    }
}
