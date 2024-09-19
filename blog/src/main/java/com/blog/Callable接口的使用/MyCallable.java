package com.blog.Callable接口的使用;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/*实现Callable<>接口*/
public class MyCallable<String> implements Callable<String> {
    /*重写call()方法,有返回值*/
    @Override
    public String call() throws Exception {
        int sum = 0;
        for (int i = 0; i <= 50; i++) {
            sum = sum+i;
            System.out.println(Thread.currentThread().getName()+":"+sum);
        }
        return (String) (Thread.currentThread().getName()+"计算的50以内的整数和为"+sum);
    }
}

class TestMyCallable{
    /*这是一个程序入口*/
    public static void main(String[] args) {

        /*创建实现Callable接口的类的实例化对象*/
        MyCallable<String> myCallable = new MyCallable<>();
        /*创建FutureTask类的实例化对象,并传入Callable接口的实例化对象*/
        FutureTask<String> futureTask = new FutureTask<>(myCallable);
        /*创建Thread类的实例化对象,传入FutureTask的实例化对象*/
        Thread thread = new Thread(futureTask,"线程1号");
        /*创建实现Callable接口的类的实例化对象*/
        MyCallable<String> myCallable1 = new MyCallable<>();
        /*创建FutureTask类的实例化对象,并传入Callable接口的实例化对象*/
        FutureTask<String> futureTask1 = new FutureTask<>(myCallable1);
        /*创建Thread类的实例化对象,传入FutureTask的实例化对象*/
        Thread thread1 = new Thread(futureTask1,"线程2号");
        /*启动线程*/
        thread.start();
        thread1.start();
        /*通过FutureTask的get方法获取返回值*/
        try {
            System.out.println(futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
