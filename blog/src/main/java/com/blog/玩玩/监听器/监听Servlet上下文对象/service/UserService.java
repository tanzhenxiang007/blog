package com.blog.玩玩.监听器.监听Servlet上下文对象.service;

import com.blog.pojo.User2;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    /**
     * 获取用户信息
     * @return
     */
    public User2 getUser() {
        // 实际中会根据具体的业务场景，从数据库中查询对应的信息
        return new User2(1, "测试", UUID.randomUUID().toString());
    }

    public static void main(String[] args) {
        System.out.println(UUID.randomUUID());
    }
}
