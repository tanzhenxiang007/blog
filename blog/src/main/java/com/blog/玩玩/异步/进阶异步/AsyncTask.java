package com.blog.玩玩.异步.进阶异步;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AsyncTask {

    @SneakyThrows
    @Async("otherPoolTaskExecutor") //为@Async指定线程池名字，这里使用的是自定义线程池 配置在SyncConfiguration类里
    public void doTask1() {
        long t1 = System.currentTimeMillis();
        Thread.sleep(2000);
        long t2 = System.currentTimeMillis();
        log.info("task1 cost {} ms" , t2-t1);
    }

    @SneakyThrows
    @Async // 我在AsyncConfiguration类中修改配置类让其实现AsyncConfigurer，并重写getAsyncExecutor()方法，指定默认线程池
    //   若没有修改，则会使用原始的异步 ，没有使用自定义的线程池，若系统中不断的创建线程，最终会导致系统占用内存过高，引发OutOfMemoryError错误
    public void doTask2() {
        long t1 = System.currentTimeMillis();
        Thread.sleep(3000);
        long t2 = System.currentTimeMillis();
        log.info("task2 cost {} ms" , t2-t1);
    }

    public static void main(String[] args) {
        String a="11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111";
        if (a.length()>201){

            System.out.println(a.length());
        }
        System.out.println(a.length());}
}