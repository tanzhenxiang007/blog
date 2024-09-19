package com.blog.玩玩.多线程队列;



import java.util.concurrent.*;

class DelayTask implements Delayed {

    private long delay; //延迟多少纳秒开始执行
    private TimeUnit unit;

    public DelayTask(long delay, TimeUnit unit){
        this.unit = unit;
        this.delay = TimeUnit.NANOSECONDS.convert(delay, unit);//统一转换成纳秒计数
    }

    @Override
    public long getDelay(TimeUnit unit) {//延迟剩余时间，单位unit指定
        return unit.convert(delay - System.currentTimeMillis(), TimeUnit.NANOSECONDS);
    }

    @Override
    public int compareTo(Delayed o) {//基于getDelay实时延迟剩余时间进行比较
        if(this.getDelay(TimeUnit.NANOSECONDS) < o.getDelay(TimeUnit.NANOSECONDS)) //都换算成纳秒计算
            return -1;
        else if(this.getDelay(TimeUnit.NANOSECONDS) > o.getDelay(TimeUnit.NANOSECONDS)) //都换算成纳秒计算
            return 1;
        else
            return 0;
    }

    @Override
    public String toString() {
        return "DelayTask{" +
                "delay=" + delay +
                ", unit=" + unit +
                '}';
    }
}

 class Demo {
    public static void main(String[] args) {
//        DelayQueue<DelayTask> dq = new DelayQueue();
//
//        //入队四个元素，注意它们的延迟时间单位不一样。
//        dq.offer(new DelayTask(5, TimeUnit.SECONDS));
//        dq.offer(new DelayTask(2, TimeUnit.MINUTES));
//        dq.offer(new DelayTask(700, TimeUnit.MILLISECONDS));
//        dq.offer(new DelayTask(1000, TimeUnit.NANOSECONDS));
//
//        while(dq.size() > 0){
//            try {
//                System.out.println(dq.take());
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        }
        String geta = geta(0);
        System.out.println(geta);
    }

    public  static String geta(int b){
        try {
          int o= b%0;
            System.out.println(o);

        }catch (Exception e){
            System.out.println("报错了");
        }
        return "123";
    }
}
