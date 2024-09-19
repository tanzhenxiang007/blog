package com.blog.玩玩.监听器.SpringBoot中自定义事件监听.service;

import com.blog.pojo.User2;
import com.blog.玩玩.监听器.SpringBoot中自定义事件监听.自定义事件.MyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * UserService
 * @author shengwu ni
 */
@Service
public class UserService2 {

    @Resource
    private ApplicationContext applicationContext;

    /**
     * 发布事件
     * @return
     */
    public User2 getUser2(User2 user) {
//        User2 user = new User2(1, "测试", "男");
        // 发布事件
        MyEvent event = new MyEvent(this, user);
        applicationContext.publishEvent(event);
        return user;
    }
}
