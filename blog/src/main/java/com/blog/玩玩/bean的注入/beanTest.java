package com.blog.玩玩.bean的注入;

import com.alibaba.fastjson.JSON;
import com.blog.pojo.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class beanTest {
    @Bean(name = "json1")
    public User getJson()
    {
        //方式一
//        User user = new User();
//        user.setAvatar("123");
//        user.setEmail("123");
//        user.setId(12l);

        //方式二
//        JSONObject jsonObject=new JSONObject();
//        jsonObject.put("id",12l);
//        jsonObject.put("username","123");
//        User user2=jsonObject.toJavaObject(User.class);

        //方式三
        String jsonObject1 = "{\"password\":null,\"createTime\":null,\"blogs\":[],\"nickname\":null,\"updateTime\":null,\"avatar\":\"123\",\"id\":12,\"type\":null,\"email\":\"123\",\"username\":null}";
        User user1 = JSON.parseObject(jsonObject1, User.class);


        return user1;
    }
}
