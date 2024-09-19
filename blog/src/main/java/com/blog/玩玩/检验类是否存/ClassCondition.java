package com.blog.玩玩.检验类是否存;


import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class ClassCondition implements Condition {
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        boolean flag = false;
        //下面方法可以用于检验某个.class文件是否存在
        try {
            Class.forName("org.springframework.data.redis.core.RedisTemplate");
            flag = true;
        } catch (ClassNotFoundException e) {
            flag = false;
        }
        return flag;
    }
}
