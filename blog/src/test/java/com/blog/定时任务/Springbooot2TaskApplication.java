package com.blog.定时任务;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootTest
@EnableScheduling //这个注解的作用就是开启定时任务功能 利用springtask即时spring的自己的技术
public class Springbooot2TaskApplication {
    public static void main(String[] args) {
        SpringApplication.run(Springbooot2TaskApplication.class, args);
    }
}
