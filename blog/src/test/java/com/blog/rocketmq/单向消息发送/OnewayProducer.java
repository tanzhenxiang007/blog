package com.blog.rocketmq.单向消息发送;


import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

public class OnewayProducer {
    public static void main(String[] args)throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("cg");
        producer.setNamesrvAddr("127.0.0.1:9876");
        //启动生产者
        producer.start();
        for (int i = 0; i < 10; i++) {
            byte[] msgBody = ("hi," + i).getBytes();
            Message message = new Message("single", "singleTag", msgBody);
            //单向发送，没有消息的返回值，所以也不用打印
            producer.sendOneway(message);
        }
        producer.shutdown();
        System.out.println("producer 关闭");
    }
}

