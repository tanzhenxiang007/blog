package com.blog.定时任务;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SpringTaskJob {
    @Scheduled(cron ="0/1 * * * * ?")
    public void task(){

        System.out.println(Thread.currentThread().getName()+"定时任务");
    }
}
