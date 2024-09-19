package com.blog.玩玩.redis发布订阅.第一种redissson;


import com.alibaba.fastjson.JSONObject;
import com.blog.玩玩.NFS.StringUtils;
import com.blog.玩玩.redis发布订阅.第二种redisTemplate.ChannelConstant;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.redisson.api.listener.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.function.Consumer;

@RestController
@RequestMapping("redis")
@Slf4j
public class PublishController {
    @Autowired
    private RedissonClient redissonClient;


    /**
     * 先请求 http://localhost:8081/redis/subscribe1 后请求 http://localhost:8081/redis/publish?msg=4
     * RTopic适用于任何使用Redisson作为客户端库的环境。
     *Redisson的RTopic是一个基于Redisson客户端的发布/订阅功能实现，提供了一种更为直接的方式来监听频道消息。
     */
    @RequestMapping(value = "/subscribe1")
    //
    public void subscribe1(){

        RTopic topic = redissonClient.getTopic(ChannelConstant.CHANNEL_GLOBAL_NAME);
        topic.addListener(String.class, new MessageListener<String>() {
            @Override
            //当有消息发布到ChannelConstant.CHANNEL_GLOBAL_NAME这个频道时，
            // 所有的订阅者（包括你的subscribe1方法中的订阅者）都会触发它们的监听器，并且onMessage方法会被自动调用
            public void onMessage(CharSequence charSequence, String s) {
                System.out.println("subscribe1收到消息：" + s);
            }
        });
        System.out.println("订阅成功等待消息");
        //死循环 保持连接
        while (true){


        }
    }

    @RequestMapping(value = "/subscribe2")
    public void subscribe2(){

        RTopic topic = redissonClient.getTopic(ChannelConstant.CHANNEL_GLOBAL_NAME);
        topic.addListener(String.class, new MessageListener<String>() {
            @Override
            public void onMessage(CharSequence charSequence, String s) {
                System.out.println("subscribe2收到消息：" + s);
            }
        });
        System.out.println("订阅成功等待消息");
        //死循环 保持连接
        while (true){


        }
    }

    @RequestMapping(value = "/publish")
    public void publish(@RequestParam(value = "msg") String msg){
        RTopic topic = redissonClient.getTopic(ChannelConstant.CHANNEL_GLOBAL_NAME);
        int i = topic.countListeners();
        System.out.println("当前订阅者数量：" + i);
        topic.publish(msg);
    }




    @PostConstruct //@PostConstruct注解的方法通常用作初始化方法，进行一些启动时的设置或准备工作
    public void take() {
        new Thread(() -> {
            RTopic topic = redissonClient.getTopic(ChannelConstant.CHANNEL_GLOBAL_NAME);

                log.info("subscribe3开始监听=="+ChannelConstant.CHANNEL_GLOBAL_NAME);
                try {

                    topic.addListener(String.class, new MessageListener<String>() {
                        @Override
                        public void onMessage(CharSequence charSequence, String s) {
                            log.info("subscribe3收到消息：" + s);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }

        }).start();
    }





    /**
     * 此处传json格式数据
     * @param jsonObject
     */
    @RequestMapping(value = "/jsonPublish")
    public void jsonPublish(@RequestBody JSONObject jsonObject){
        RTopic topic = redissonClient.getTopic(ChannelConstant.CHANNEL_SINGLE_NAME);
        int i = topic.countListeners();
        System.out.println("当前订阅者数量：" + i);
        topic.publish(jsonObject.toJSONString());
    }

    @PostConstruct //@PostConstruct注解的方法通常用作初始化方法，进行一些启动时的设置或准备工作
    public void take2() {
        new Thread(() -> {

            init();
        }).start();
    }

    public void init() {
        listenTaskPublish(String.class, eventMsg -> {
            JSONObject jsonObject = JSONObject.parseObject(eventMsg);
            EventMsg message = jsonObject.toJavaObject(EventMsg.class);
            String event = message.getEvent();
            String taskId = message.getTaskId();
            if(StringUtils.equals("addTask", event)) {
                System.out.println("处理添加任务的操作=="+taskId);
            } else if(StringUtils.equals("removeTask", event)) {
                System.out.println("处理删除任务的操作=="+taskId);
            }else {
                log.error("未知操作");
            }
        });
        log.info("初始化任务操作");
    }

    public <T> void listenTaskPublish(Class<T> clazz, Consumer<T> consumer) {
        try {

            RTopic topic = redissonClient.getTopic(ChannelConstant.CHANNEL_SINGLE_NAME);
            topic.addListener(clazz, (channel, msg) -> consumer.accept(msg));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
