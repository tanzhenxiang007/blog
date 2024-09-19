package com.blog.玩玩;

import java.util.concurrent.LinkedBlockingDeque;

public class MyRunnable implements Runnable {
    @Override
    public void run() {

        // 线程执行的逻辑
        System.out.println("线程开始执行...");
        try {
            Thread.sleep(3000); // 模拟线程执行了3秒
            System.out.println("线程名："+Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程执行完毕。");
    }
}

 class Main {
    public static void main(String[] args) {
        // 创建线程实例
        MyRunnable myRunnable = new MyRunnable();
        Thread thread = new Thread(myRunnable,"tzx-thread-");
        // 启动线程
        thread.start();
        thread.run();// 这里直接查看main方法的线程名称  注意的是主方法也是进程的一个线程
        System.out.println("主线程开始执行...");
    }
}
