//package com.blog.controller.redis.分布式锁;
//
//import org.redisson.Redisson;
//import org.redisson.api.RedissonClient;
//import org.redisson.config.Config;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class RedissonConfig {
//
////    @Value("${spring.redis.host}")
////    private String host;
////
////    @Value("${spring.redis.port}")
////    private String port;
//
//    @Bean
//    public RedissonClient redissonClient(){
//        Config config = new Config();
//        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
//        RedissonClient client = Redisson.create(config);
//        return client;
//    }
//}