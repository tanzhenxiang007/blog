package com.blog.玩玩.延时任务篇.redisson实现延迟任务实战.订单消费任务;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBlockingQueue;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;


/**
 * @date 2023/8/30 15:09
 */
@Slf4j
@Component
public class OrderTask {

    //这行代码是在另一个Spring管理的bean中声明的，Spring容器会查找已注册的bean，
    // 并自动注入一个匹配的RBlockingQueue类型的bean到这个字段中
    // @Resource(name = "blockingQueue") // 指定注入名为"blockingQueue"的bean
    @Resource
    private RBlockingQueue<Object> blockingQueue;

    @PostConstruct
    public void take() {
        new Thread(() -> {
            while (true) {
                log.info("开始消费");
                try {
                    //将到期的数据取出来，如果一直没有到期数据，就一直等待。
                    //RBlockingQueue的take()方法会阻塞调用线程，直到队列中存在至少一个元素可用。如果队列为空，调用take()方法的线程将一直等待，直到其他线程向队列中放入了元素。
                   //take()方法从队列中移除并返回头部元素，如果队列为空，则等待（阻塞）直到有元素可用
                    //由于take()方法可能会无限期地阻塞，所以它通常在循环中使用，以便在获取元素后继续执行循环体中的其他操作
                    log.info("将到期的数据取出来=="+blockingQueue.take().toString());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

