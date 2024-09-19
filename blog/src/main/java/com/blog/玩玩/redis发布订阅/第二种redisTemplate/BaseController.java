package com.blog.玩玩.redis发布订阅.第二种redisTemplate;


import com.blog.pojo.User2;
import com.blog.study.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

//参考：https://blog.csdn.net/yprufeng/article/details/136151115?spm=1001.2101.3001.6650.12&utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromBaidu%7ERate-12-136151115-blog-129151277.235%5Ev43%5Epc_blog_bottom_relevance_base2&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromBaidu%7ERate-12-136151115-blog-129151277.235%5Ev43%5Epc_blog_bottom_relevance_base2&utm_relevant_index=23
//创建一个http请求，用于发布基于redis的消息供客户端订阅
@RequestMapping(value = "base")
@RestController
@Slf4j
public class BaseController {
    @Resource
    private RedisTemplate redisTemplate;

    @GetMapping(value = "/getJsonResult")
    public JsonResult getJsonResult() {

        JsonResult jsonObject=new JsonResult();

            return jsonObject.ok().put("data",new User2(1, "倪升武", "123456"));

    }

    @GetMapping(value = "/publish/{msg}")

    public JsonResult sendMsg(@PathVariable(value = "msg") String msg) {
        JsonResult result=new JsonResult();
        try {
            redisTemplate.convertAndSend(ChannelConstant.CHANNEL_GLOBAL_NAME, msg);
          return   result.ok();
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return   result.error();
        }

    }
}
