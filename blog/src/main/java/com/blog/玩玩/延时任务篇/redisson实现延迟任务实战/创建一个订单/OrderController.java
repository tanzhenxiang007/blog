package com.blog.玩玩.延时任务篇.redisson实现延迟任务实战.创建一个订单;


import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RDelayedQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@Slf4j
@RequestMapping(value = "api/order")
@RestController
public class OrderController {
    @Autowired
    private RDelayedQueue delayedQueue;

    /**
     * 参考文章 https://blog.csdn.net/yprufeng/article/details/140830639?spm=1001.2014.3001.5502
     * @param orderId
     * @return
     */

    @RequestMapping("saveOrderByRedisson")
    public JSONObject saveOrderByRedisson(@RequestParam(value = "orderId") String orderId) {
        JSONObject jsonObject = new JSONObject();
//        String orderId = String.valueOf(orderId);
        log.info("订单创建成功，5秒内等待支付=="+orderId);
        delayedQueue.offerAsync(orderId, 5, TimeUnit.SECONDS);
        return jsonObject;
    }
}
