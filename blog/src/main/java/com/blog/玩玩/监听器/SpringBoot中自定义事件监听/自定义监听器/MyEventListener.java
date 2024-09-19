package com.blog.玩玩.监听器.SpringBoot中自定义事件监听.自定义监听器;

import com.blog.pojo.User2;
import com.blog.玩玩.监听器.SpringBoot中自定义事件监听.自定义事件.MyEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 自定义监听器，监听MyEvent事件
 */
@Component
@Slf4j
public class MyEventListener implements ApplicationListener<MyEvent> {
    @Override
    public void onApplicationEvent(MyEvent myEvent) {
        // 把事件中的信息获取到
        User2 user = myEvent.getUser();
        // 处理事件，实际项目中可以通知别的微服务或者处理其他逻辑等等
        log.info("自定义监听器接收到信息，实际项目中可以通知别的微服务或者处理其他逻辑等等");
        log.info("用户名：" + user.getName());
        log.info("性别：" + user.getSex());

    }
}