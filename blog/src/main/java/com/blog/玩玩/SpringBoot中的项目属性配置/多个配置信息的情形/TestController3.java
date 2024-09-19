package com.blog.玩玩.SpringBoot中的项目属性配置.多个配置信息的情形;

import com.blog.study.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/test3")
public class TestController3 {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestController3.class);

    @Resource
    private MicroServiceUrl microServiceUrl;

    @RequestMapping("/config3")
    public JsonResult testConfig() {
        LOGGER.info("=====获取的订单服务地址为：{}", microServiceUrl.getOrderUrl());
        LOGGER.info("=====获取的用户服务地址为：{}", microServiceUrl.getUserUrl());
        LOGGER.info("=====获取的购物车服务地址为：{}", microServiceUrl.getShoppingUrl());

        return new JsonResult().ok();
    }
}
