package com.blog.rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class Consumer {
    public static void main(String[] args) {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("pg");

        consumer.setNamesrvAddr("127.0.0.1:9876");
        consumer.setInstanceName("rmq-instance");
        try {
            consumer.subscribe("broker-a", "tag");
            consumer.registerMessageListener(new MessageListenerConcurrently()
            {
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                                                                ConsumeConcurrentlyContext context) {
                    for (MessageExt messageExt : msgs) {
                        //获取消息key
                        String keys = messageExt.getKeys();
                        //获取消息队列id
                        int queueId = messageExt.getQueueId();
//                    获取消息主题
                        String topic = messageExt.getTopic();
//                    获取消息标签
                        String tags = messageExt.getTags();
//                    获取消息体内容
                        String body = null;
                        try {
                            body = new String(messageExt.getBody(), "UTF-8");
                        } catch (UnsupportedEncodingException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println("topic："+topic+"，tags："+tags+"，body："+body+"，queueId："+queueId+"，keys："+keys);
                    }
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            });
            consumer.start();
            System.out.println("Consumer Started.");
        }
        catch (MQClientException e) {
            e.printStackTrace();
        }

    }
}
