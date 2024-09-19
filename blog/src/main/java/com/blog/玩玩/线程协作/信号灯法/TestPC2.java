package com.blog.玩玩.线程协作.信号灯法;

//测试生产者消费者问题2：信号灯法-->标志位解决
public class TestPC2 {
    public static void main(String[] args) {
        TV tv = new TV();
        new Player(tv).start();
        new Watcher(tv).start();
    }
}

//生产者--->演员
class Player extends Thread{
    TV tv;
    public Player(TV tv){
        this.tv = tv;
    }

    @Override
    public void run(){
        for (int i = 0; i < 20; i++) {
            if(i%2==0){
                try {
                    this.tv.play("快乐大本营播放中");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }else{
                System.out.println("广告时间。。。。");
            }
        }
    }
}

//消费者--->观众
class Watcher extends Thread{
    TV tv;
    public Watcher(TV tv){
        this.tv = tv;
    }

    @Override
    public void run(){
        for (int i = 0; i < 20; i++) {
            try {
                tv.watch();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}


//产品--->节目
class TV{
    //演员表演，观众等待 T
    //观众观看，演员等待 F
    String program ;//表演节目
    boolean flag = true;

    //表演
    public synchronized void play(String program) throws InterruptedException {
        if(!flag){
            this.wait();
        }
        System.out.println("演员表演--> "+program);
        //通知观众观看
        this.notifyAll();//通知唤醒
        this.program = program;
        this.flag = !this.flag;
    }
    //观看
    public synchronized void watch() throws InterruptedException {
        if(flag){
            this.wait();
        }
        System.out.println("观看了"+program);
        //通知演员表演
        this.notifyAll();
        this.flag = !this.flag;
    }
}
