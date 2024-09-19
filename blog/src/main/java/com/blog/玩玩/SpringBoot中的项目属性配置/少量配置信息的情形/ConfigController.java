package com.blog.玩玩.SpringBoot中的项目属性配置.少量配置信息的情形;

import com.blog.study.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class ConfigController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigController.class);
//@Value 注解上通过 ${key} 即可获取配置文件中和 key 对应的 value 值
    @Value("${url.orderUrl}")
    private String orderUrl;

    @RequestMapping("/config")
    public JsonResult testConfig() {
        LOGGER.info("=====获取的订单服务地址为：{}", orderUrl);
        return new JsonResult().ok();
    }
}
