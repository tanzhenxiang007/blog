package com.blog.测试;

import org.springframework.stereotype.Component;

@Component
public class User {



    public void sayHi(String name){

        System.out.println(name+"你好");
    }

}

