package com.blog.玩玩.异步;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author tzx
 */
public class Async {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//         completableFuture1();
//        future1();
        CallableAndExecutor();
    }





    //使用Java的CompletableFuture类：CompletableFuture类提供了一种方便的方式来执行异步操作。
    // 你可以使用CompletableFuture的静态方法supplyAsync()或runAsync()来执行异步操作。例如：
    private static void completableFuture1() throws ExecutionException, InterruptedException {

        int i = 10;
        System.out.println("异步代码前");
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            // 异步操作
            for (int i1 = 0; i1 < i; i1++) {
                System.out.println(i1);
            }
            return "异步操作结束";
        });
        System.out.println("异步代码后");
        String result = future.get();
        System.out.println("输出异步结果：" + result);
    }









    //使用Java的Future接口：Future接口表示一个异步计算的结果。你可以使用ExecutorService的submit()方法来提交一个Callable任务，
    // 并获取一个Future对象来跟踪异步操作的状态和获取结果。例如：
    public static void future1() throws ExecutionException, InterruptedException {

        // 声明并初始化一个整型变量 i，赋值为 50
        int i = 50;

        // 创建一个固定大小的线程池，大小为2。线程池是一个能够管理并控制线程的工具，可以重用已存在的线程，减少创建和销毁线程的开销。
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        // 打印"异步代码前"，用于标识异步操作开始前的状态。
        System.out.println("异步代码前");

        // 使用 executorService 的 submit 方法提交一个 Callable 任务，并返回一个 Future 对象。这个 Future 对象代表了一个还未计算完成的任务的结果。
        Future<String> future = executorService.submit(() -> {
            // 异步操作：在新的循环中，打印循环计数器和当前线程的名字，从0到49次。
            for (int i1 = 0; i1 < i; i1++) {
                System.out.println("第一个循坏：" + i1);
                System.out.println(Thread.currentThread().getName());
            }
            // 返回一个字符串"异步操作结束1"
            return "异步操作结束1";
        });

        // 再次使用 executorService 的 submit 方法提交另一个 Callable 任务。这个任务会打印第二个循环计数器和当前线程的名字，从0到49次。
        executorService.submit(() -> {
            for (int i1 = 0; i1 < i; i1++) {
                System.out.println("第二个循坏：" + i1);
                System.out.println(Thread.currentThread().getName());
            }
            return "异步操作结束2";
        });

        // 第三次使用 executorService 的 submit 方法提交另一个 Callable 任务。这个任务会打印第三个循环计数器和当前线程的名字，从0到49次。
        executorService.submit(() -> {
            for (int i1 = 0; i1 < i; i1++) {
                System.out.println("第三个循坏：" + i1);
                System.out.println(Thread.currentThread().getName());
            }
            return "异步操作结束3";
        });

        // 打印"异步代码后"，用于标识异步操作提交后、结果获取前的状态。这并不意味着所有的异步操作都已经完成，
        // 只是表明所有的异步操作都已经提交到线程池中。
        System.out.println("异步代码后");

        // 使用 Future 的 get 方法来获取任务的结果。这个方法会阻塞，直到任务完成。如果任务还未完成，这个方法会一直等待。
        // 此行代码会阻塞，直到 future 这个任务完成并返回结果。
        String result = future.get();//这里只返回第一个异步任务

        // 提交所有已经提交的任务执行完毕后，关闭线程池。需要注意的是，即使调用了 shutdown() 或 shutdownNow() 方法，
        // 如果线程池中的某个任务正在执行，那么这个任务仍可能会继续执行，直到完成。此后不再接受新的任务，
        // 但会处理已经提交的任务。当所有任务完成后，线程池才会被关闭。
        executorService.shutdown();

        // 打印"输出异步结果："和前面获取的任务结果字符串。
        System.out.println("输出异步结果：" + result);
    }















    //使用Java的Callable和Executor框架：Callable接口表示一个有返回值的计算任务，可以与Executor框架一起使用来执行异步操作。例如：
    private static void CallableAndExecutor() throws ExecutionException, InterruptedException {

        // 创建一个固定大小的线程池，大小为2。线程池是一个能够管理并控制线程的工具，可以重用已存在的线程，减少创建和销毁线程的开销。
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        // 打印"异步代码前"，用于标识异步操作开始前的状态。
        System.out.println("异步代码前");

        // 创建一个用于存储Future<String>对象的列表，Future表示异步计算的结果。
        List<Future<String>> futures = new ArrayList<>();

        // 循环10次。
        for (int i = 0; i < 10; i++) {
            // 将循环变量i设为final，以保证在Lambda表达式中能正确地访问和使用这个变量。
            int finalI = i;
            // 创建一个Callable对象，它是一个返回String类型结果的函数式接口。在这个函数中，会打印当前线程的名字，并返回一个包含循环变量i的字符串。
            Callable<String> callable = () -> {
                // 异步操作：打印当前线程的名字。
                System.out.println("第" + finalI+"任务线程名："+Thread.currentThread().getName());
                // 返回一个包含循环变量finalI的字符串。
                return "第" + finalI+"任务结束";
            };
            // 使用executorService的submit方法提交callable任务，并返回一个Future对象。这个Future对象代表了一个还未计算完成的任务的结果。
            futures.add(executorService.submit(callable));
        }

        // 打印"异步代码后"，用于标识异步操作结束后的状态。这并不意味着所有的异步操作都已经完成，只是表明所有的异步操作都已经提交到线程池中。
        System.out.println("异步代码后");

        // 遍历futures列表中的每一个Future对象。
        for (Future<String> future : futures) {
            // 使用Future的get方法来获取任务的结果。这个方法会阻塞，直到任务完成。如果任务还未完成，这个方法会一直等待。
            String result = future.get();
            // 打印任务的结果。
            System.out.println(result);
        }

        // 关闭executorService。关闭后，线程池将不再接受新的任务，但会继续处理已提交的任务。当所有任务都完成后，线程池会被关闭。
        executorService.shutdown();
    }


}
