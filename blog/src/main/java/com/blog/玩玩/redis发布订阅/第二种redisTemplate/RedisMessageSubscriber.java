package com.blog.玩玩.redis发布订阅.第二种redisTemplate;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;

// 创建一个消息订阅者，实现MessageListener接口，通过重写onMessage方法订阅消息
@Slf4j
@Component
public class RedisMessageSubscriber implements MessageListener {

    //此处的bean在com/blog/util/RedisConfig.java 内注入一个初始化的初始化一个Redis消息监听容器
    //RedisMessageListenerContainer适用于Spring Data Redis环境，与Spring框架紧密集成。
    //RedisMessageListenerContainer是Spring Data Redis提供的一个容器，用于简化Redis消息监听器的配置和管理
    @Autowired
    private RedisMessageListenerContainer redisMessageListenerContainer;

    /**
     * 订阅消息：将订阅者添加到指定的频道
     */
    @PostConstruct
    //
    public void subscribeToChannel() {
        //广播消息
        redisMessageListenerContainer.addMessageListener(this, new ChannelTopic(ChannelConstant.CHANNEL_GLOBAL_NAME));
    }

    @Override
    public void onMessage(Message message, byte[] bytes) {
        String channel = new String(message.getChannel(), StandardCharsets.UTF_8);
        String messageBody = new String(message.getBody(), StandardCharsets.UTF_8);
        log.info("接收到的信息: " + messageBody + " 来自订阅的通道: " + channel);
    }
}