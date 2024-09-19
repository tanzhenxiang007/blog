package com.blog.rocketmq;


import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

public class AsyncProducer {
    public static void main(String[] args)throws  Exception {
        //创建一个生产者
        DefaultMQProducer producer = new DefaultMQProducer("nq");
        //指定服务器地址
        producer.setNamesrvAddr("127.0.0.1:9876");
        //指定异步发送失败后，不进行重试发送,也可改成2
        producer.setRetryTimesWhenSendAsyncFailed(0);
        //指定新创建的topic的queue数量为2，默认为4
        producer.setDefaultTopicQueueNums(2);
        //设置关闭vip通道
//        producer.setVipChannelEnabled(false);
        //设置发送超时实现为6秒，默认是3秒
        producer.setSendMsgTimeout(6000);
        //启动生产者
        producer.start();
        //发送100条消息
        for (int i = 0; i < 100; i++) {
            //创建一个消息体
            byte[] body = ("hi," + i).getBytes();
            Message message = new Message("broker-c", "tag", body);
            //异步发送需要有callback回调方法
            producer.send(message, new SendCallback() {
                //当producer接收到mq发送来的ack后就会触发该回调方法的执行
                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.println(sendResult);
                }

                @Override
                public void onException(Throwable throwable) {
                    throwable.printStackTrace();
                }
            });

        }//end-for
        //sleep一会了，由于是异步发送，这里如果不sleep,则消息还未发送就会将producer给关闭了
        Thread.sleep(3000);
        producer.shutdown();
    }
}

