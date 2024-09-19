package com.blog.玩玩;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class ThreadPoolExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 创建一个线程池，最大线程数为 5，核心线程数为 3，线程存活时间为 60 秒
        ExecutorService executorService = Executors.newFixedThreadPool(5, r -> {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        });

        // 创建 10 个 Callable 任务
        Callable<Integer>[] tasks = new Callable[10];
        for (int i = 0; i < 10; i++) {
            tasks[i] = new MyCallable(i);
        }

        // 提交 10 个 Callable 任务给线程池执行
        FutureTask<Integer>[] futures = new FutureTask[10];
        for (int i = 0; i < 10; i++) {
            futures[i] = new FutureTask<>(tasks[i]);
            executorService.execute(futures[i]);
        }

        // 获取 10 个 Callable 任务的执行结果
        int[] results = new int[10];
        for (int i = 0; i < 10; i++) {
            results[i] = futures[i].get();
        }

        // 关闭线程池
        executorService.shutdown();
    }

    static class MyCallable implements Callable<Integer> {
        private int taskId;

        public MyCallable(int taskId) {
            this.taskId = taskId;
        }

        @Override
        public Integer call() throws Exception {
            System.out.println("线程 " + Thread.currentThread().getName() + " 执行任务 " + taskId);
            return taskId * 2;
        }
    }
}