package com.blog.玩玩.监听器.SpringBoot中自定义事件监听.自定义事件;

import com.blog.pojo.User2;
import org.springframework.context.ApplicationEvent;

/**
 * 自定义事件
 */

public class MyEvent extends ApplicationEvent {

    private User2 user;

    public MyEvent(Object source, User2 user) {
        super(source);
        this.user = user;
    }

    public User2 getUser() {
        return user;
    }

    public void setUser(User2 user) {
        this.user = user;
    }
}