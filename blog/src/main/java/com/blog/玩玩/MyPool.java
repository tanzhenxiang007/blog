package com.blog.玩玩;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;


public class MyPool {
    private static Logger logger = LoggerFactory.getLogger(MyPool.class);

    /*这是一个程序入口*/
    public static void main(String[] args) {
        /*①使用线程池的工厂类Executors提供的静态方法newFixedThreadPool生产一个指定数量的线程池*/
//        ExecutorService pool = Executors.newFixedThreadPool(3);
//        ExecutorService pool = new ThreadPoolExecutor(3,5,0L, TimeUnit.MILLISECONDS,new LinkedBlockingQueue());

            ThreadPoolExecutor pool = new ThreadPoolExecutor(
                    5,
                    10,
                    60,
                    TimeUnit.SECONDS,
                    new ArrayBlockingQueue<>(100000));
            
            /*④调用ExecutorService中的submit()方法,传递线程任务,开启线程*/
        try {
            Future<Integer> submit = pool.submit(new Task());
            // 处理Future的结果，如果需要
            Integer result = submit.get();
            System.out.println("result:" + result);
        } catch (Exception e) {
            logger.error("Error executing task: " + e.getMessage());
            // 根据需要处理异常
        }


        /*③创建线程任务对象*/
        Thread1 t1 = new Thread1();
        /*③创建线程任务对象*/
        Thread2 t2 = new Thread2();
        /*④调用ExecutorService中的submit()方法,传递线程任务,开启线程*/
        pool.submit(t1);
        pool.submit(t2);
        pool.submit(t1);
        pool.submit(t2);
        pool.submit(t1);
        pool.submit(t2);
        pool.submit(t1);
        pool.submit(t2);
        /*⑤调用ExecutorService中的shutdown()方法销毁线程池*/
        pool.shutdown();
    }

    private static class Task implements Callable<Integer> {
        @Override
        public Integer call() {
            try {
                int i = 1 / 0;
                return i;
            } catch (ArithmeticException e) {
                logger.error("Arithmetic exception occurred: " + e.getMessage());
                // 根据需要处理异常
                return null;
            }
        }
    }
}


/*②创建线程任务类*/
class Thread1 implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName()+":"+"完成任务!!!");
        }
    }
}

/*②创建线程任务类*/
class Thread2 implements Callable<String> {

    @Override
    public String call() throws Exception {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName()+":完成任务!!!");
        }
        return null;
    }

}
