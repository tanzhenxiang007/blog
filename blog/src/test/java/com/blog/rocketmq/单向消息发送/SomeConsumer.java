package com.blog.rocketmq.单向消息发送;


import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class SomeConsumer {
    public static void main(String[] args)throws MQClientException {
        //定义一个pull消费者
        //DefaultLitePullConsumer consumer1 = new DefaultLitePullConsumer("cg");
        //定义一个push 消费者
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("cg");
        consumer.setNamesrvAddr("127.0.0.1:9876");
        //从哪里开始消费,指定从第一条开始消费
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        //指定消费topic与tag
        consumer.subscribe("single","*");
        //指定费用广播模式  进行消费，默认为集群模式的
        //consumer.setMessageModel(MessageModel.BROADCASTING);
        //注册消息监听器
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                                                            ConsumeConcurrentlyContext context) {
                //逐条消费消息
                for (MessageExt msg : msgs ) {
//                    读取消息记录
                    for (MessageExt messageExt : msgs) {
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
                        System.out.println("topic："+topic+"，tags："+tags+"，body："+body+"，queueId："+queueId);
                    }
                }
                //返回消费状态：消费成功
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        //开启消费者进行消费
        consumer.start();
        System.out.println("消费者开始了----");
    }
}

