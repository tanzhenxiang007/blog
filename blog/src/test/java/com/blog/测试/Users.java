package com.blog.测试;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;

@Controller
public class Users {
    @Bean
    public User user1(){
        User user = new User();
        return user;
    }
}
 class TestBean {
    public static void main(String[] args) {
        //获取spring上下文
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-config.xml");
        //获取指定的Bean对象名称+类型进行获取
        User user =  (User) applicationContext.getBean("user1");
        user.sayHi("tzx");
    }
}

