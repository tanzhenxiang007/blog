package com.blog.玩玩.线程协作.管程法;

//并发协作模型：生产者消费者模式-----> 管程法（增加缓冲区）
//
//生产者：负责生产数据的模块（可能是方法，对象，线程，进程）；
//消费者：负责处理数据的模块（可能是方法，对象，线程，进程）；
//缓冲区：消费者不能直接使用生产者的数据，他们之间有个缓冲区；

//测试：生产者消费者模型--->利用缓冲区解决：管程法
//生产者，消费者，产品，缓冲区
public class TestPC {
    public static void main(String[] args) {
        //创建一个容器
        SynContainer container = new SynContainer();

        new Producer(container).start();
        new Consumer(container).start();
    }
}

//生产者
class Producer extends Thread{
    SynContainer container;
    public Producer(SynContainer container){
        this.container = container;
    }
    //生产
    @Override
    public void run() {
        for (int i = 0; i < 30; i++) {
            try {
                container.push(new Food(i));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("生产了"+i+"个好吃的");
        }
    }
}

//消费者
class Consumer extends Thread{
    SynContainer container;
    public Consumer(SynContainer container){
        this.container = container;
    }
    //消费
    @Override
    public void run(){
        for (int i = 0; i < 30; i++) {
            try {
                System.out.println("消费了--->剩余"+container.buy().id+"个好吃的");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

//产品
class Food{
    int id;//产品编号

    public Food(int id) {
        this.id = id;
    }
}

//缓冲区
class SynContainer{
    //需要一个容器大小
    Food[] foods = new Food[10];
    //容器计数器
    int count = 0 ;

    //生产者放入产品 同步方法
    public synchronized void push(Food food) throws InterruptedException {
        //如果容器满了，就需要等待消费者消费
        if(count==foods.length){
            //通知消费者消费,生产者等待
            this.wait();// 表示线程一直等待，直到其他线程通知，与sleep不同，会释放锁
        }
        //如果没有满，就需要丢入产品
        foods[count]=food;
        count++;

        //可以通知消费者消费了
        this.notifyAll();//唤醒同一个对象上所有调用wait()方法的线程，优先级别高的线程优先调度

    }

    public synchronized Food buy() throws InterruptedException {
        //判断能否消费
        if(count==0){
            //等待生产者生产，消费者等待
            this.wait();
        }
        //如果可以消费
        count--;
        Food food = foods[count];
        //吃完了，通知生产者生产
        this.notifyAll();
        return food;
    }
}

