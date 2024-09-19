package com.blog.异步线程;

import java.util.concurrent.CompletableFuture;

public class AsyncThreadExample {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("执行异步代码之前的逻辑");
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            // 异步任务代码
            System.out.println("异步任务正在执行...");
            try {
                Thread.sleep(3000); // 模拟异步任务执行了3秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("异步任务执行完毕。");
        });
        System.out.println("执行异步代码后面逻辑");
        // 等待异步任务执行完毕
        future.join();
        System.out.println("主线程执行完毕。");
    }
}