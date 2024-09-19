package com.blog.玩玩;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;


public class MyCallable implements Callable<Integer> {
    private String name;
    public MyCallable(String name) {
        this.name = name;
    }
    @Override
    public Integer call(){

        Integer sum = 0;
        for(int i = 0 ; i < 50;i++) {
            System.out.println(this.name + i);
            sum += i;
        }
        return sum;
    }


}

 class testThread {
    public static void main(String[] args)  throws Exception{
        // 实例化继承Callable接口的MyThread类
        MyCallable mt1 = new MyCallable("线程一");
        MyCallable mt2 = new MyCallable("线程二");
        MyCallable mt3 = new MyCallable("线程三");

        // FutureTask类接收继承Callable接口的MyThread的实例
        FutureTask<Integer> ft1 = new FutureTask<Integer>(mt1);
        FutureTask<Integer> ft2 = new FutureTask<Integer>(mt2);
        FutureTask<Integer> ft3 = new FutureTask<Integer>(mt3);

        // 启动多线程
        new Thread(ft1).start();
        new Thread(ft2).start();
        new Thread(ft3).start();
        System.out.println(ft1.get());
        System.out.println(ft2.get());
        System.out.println(ft3.get());
    }
}
