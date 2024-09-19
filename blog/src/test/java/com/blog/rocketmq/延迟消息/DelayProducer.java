package com.blog.rocketmq.延迟消息;


import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import java.text.SimpleDateFormat;
import java.util.Date;
//消息的生产者
public class DelayProducer {
    public static void main(String[] args)throws Exception {
        //1.创造生产者
        DefaultMQProducer producer = new DefaultMQProducer("pg");
        //2.给生产者设置名称服务器
        producer.setNamesrvAddr("127.0.0.1:9876");
        //3.开启生产者
        producer.start();
        for (int i = 0; i < 10; i++) {
            byte[] body = ("hi," + i).getBytes();
            //4.循环生产消息
            Message message = new Message("TopicB", "TagB", body);
            //指定消息的延时等级为3级，即延迟10秒
            message.setDelayTimeLevel(3);
            SendResult sendResult = producer.send(message);
            //输出消息被发送的时间
            System.out.print(new SimpleDateFormat("HH:mm:ss").format(new Date() ));
            System.out.println(","+sendResult);
        }
        //5.生产完消息后关闭生产者
        producer.shutdown();
    }
}

