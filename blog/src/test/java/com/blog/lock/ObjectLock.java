package com.blog.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ObjectLock {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(10);
        //对象锁-同一个对象--正常
//        Lock lock = new Lock("object");
//        for (int index = 1; index <= 10; index++) {
//            pool.execute(lock);
//        }
        //对象锁-不同对象--异常
        for (int index = 1; index <= 10; index++) {
            pool.execute(new Lock("object"));
        }
//        //静态对象锁-不同对象--正常
//        for (int index = 1; index <= 10; index++) {
//            pool.execute(new Lock("staticObject"));
//        }
//        //类锁-同一个对象--正常
//        Lock lock_static = new Lock("static");
//        for (int index = 1; index <= 10; index++) {
//            pool.execute(lock_static);
//        }
//        //类锁-不同对象--正常
//        for (int index = 1; index <= 10; index++) {
//            pool.execute(new Lock("static"));
//        }
        pool.shutdownNow();
    }
}