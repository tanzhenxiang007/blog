package com.blog.rocketmq;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;
import java.util.Scanner;

public class Producer {

    public static void main(String[] args)
            throws MQClientException {
        //创建一个生产者producer,参数为创建一个生产者producer Group 名称
        DefaultMQProducer producer = new DefaultMQProducer("pg");
        //指定nameserver地址
        producer.setNamesrvAddr("127.0.0.1:9876");
        //设置当前发送失败后的重试次数，默认不设置是2次
        producer.setRetryTimesWhenSendFailed(3);

        //当多个生产者消费者对象使用同一个InstanceName 时，那么他们使用的是同一个MQClientInstance ，这样一些异步线程，定时拉取topic，心跳，nettyclient等线程都是一份，可以节省资源
        producer.setInstanceName("rmq-instance");
        //设置关闭vip通道
        producer.setVipChannelEnabled(false);
        //设置发送超时实现为6秒，默认是3秒
        producer.setSendMsgTimeout(6000);
        //开启生产者
        producer.start();
        //生产并发送消息
        try {
            Message message = new Message("broker-a", "tag", "这是一条测试消息".getBytes());
            //发送消息
            producer.send(message);

            //注意：下面代码用户手动输入发送消息内容
            while (true) {
                String text = new Scanner(System.in).next();
                Message msg = new Message("broker-a", // topic
                        "tag", // tag
                        text.getBytes() // body

                );

                //为消息指定key
                msg.setKeys("key-" + Math.random() );

                //发送消息

                SendResult sendResult = producer.send(msg);
                System.out.println(sendResult);
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        //关闭producer
        producer.shutdown();
    }
}
   /* InstanceName 的作用
    当多个生产者消费者对象使用同一个InstanceName 时，那么他们使用的是同一个MQClientInstance ，这样一些异步线程，定时拉取topic，心跳，nettyclient等线程都是一份，可以节省资源。

        用于负载均衡
        消费者在负载均衡时使用InstanceName 做区分。如果在同一ip下，两个服务中的消费者使用的同样的配置。InstanceName 一样。

        比如 两个都叫 10.2.116.119@bbbbb 。这样负载均衡会认为是同一个消费者。比如他把4个MessageQueue 分个10.2.116.119@bbbbb 。那么两个10.2.116.119@bbbbb 的实例都可以消费这4个MessageQueue 。

        本来的集群消费，就会变成像广播消费一样重复消费，并发生混乱

        配置方法：同一jvm实例内如果都访问同一集群。那么尽量用同一InstanceName 。

        一个消费者或者生产者的group只能有一个生产者或者消费者对象

        同一ip下的不同jvm实例不能使用同一个InstanceName，不同ip可以
*/