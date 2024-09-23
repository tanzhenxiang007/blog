package com.blog.玩玩.检验类是否存.使用方式;

import com.blog.pojo.User;
import com.blog.玩玩.检验类是否存.ClassCondition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {
@Bean
@Conditional(ClassCondition.class) //重写Condition 判断某个类是否存在 存在才创建bean
    public User user(){
    User user = new User();
    user.setNickname("重写Condition 判断某个类是否存在 存在才创建bean");
    return user;
    }
}
