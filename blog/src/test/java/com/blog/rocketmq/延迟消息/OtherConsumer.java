package com.blog.rocketmq.延迟消息;


import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
//消息的消费者
public class OtherConsumer {
    public static void main(String[] args)throws Exception {
        //0.创造消费者
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("pg");
        //1.给消费者设置名称服务器
        consumer.setNamesrvAddr("127.0.0.1:9876");
        //2.订阅的主题和子表达式是什么
        consumer.subscribe("TopicB","*"); //subscribe订阅
        //3.设置从哪里开始消费消息
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        //4.注册消息监听器,参数是并发消息监听器，Concurrently并发的意思
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                                                            ConsumeConcurrentlyContext context) {
                for (MessageExt msg : msgs) { //逐条消费消息
                    //输出消息的消费时间
                    System.out.print(new SimpleDateFormat("HH:mm:ss").format(new Date()));
                    System.out.println(","+msg);
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        //5.开启消费者
        consumer.start();
        System.out.println("消费者已开启消费---");
    }
}

