package com.blog.线程;


/**
 * @author tzx
 *
 * @functon 多线程学习
 * @time 2015.3.9
 */
class Thread1 extends Thread {
    private int ticket = 10;

    //    private String name;
//    public Thread1(String name) {
//        this.name=name;
//    }
    public void run() {
        while (true) {
            synchronized (this) {

                if (ticket < 0) {
                    System.out.println(Thread.currentThread().getName() + "的票已经全部售完，此时的票数量为：" + ticket);
                    break;
                }
                try {
                    Thread.sleep(1300); // 延迟1秒，使得ticket可以被其它线程充分改变(可能此时的ticket小于等于0了)
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " 正在售票,还剩余票数为：" + ticket--);
            }
        }
    }
}

class Main {

    public static void main(String[] args) {
        Thread1 mTh1 = new Thread1();
//        Thread1 mTh2=new Thread1("B");
        // 注意：start()方法的调用后并不是立即执行多线程代码，而是使得该线程变为可运行态（Runnable），什么时候运行是由操作系统决定的。
        new Thread(mTh1, "A").start();
        new Thread(mTh1, "B").start();
//        mTh2.start();

    }


}
