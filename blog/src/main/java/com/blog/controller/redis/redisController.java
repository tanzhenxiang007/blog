package com.blog.controller.redis;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.blog.pojo.Blog;
import com.blog.service.BlogService;
import com.blog.service.TagService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/redisUtils")
public class redisController {
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    private BlogService blogService;
    @Autowired
    TagService tagService;

    @RequestMapping("/getRedis")
    @ResponseBody  //如果有需要直接返回的数据，那么在方法上加@ResponseBody即可，而不会请求到页面。
    public Map<String,Object> getRedis(@RequestParam Map<String,Object> paramMap){
        Map<String,Object> resultMap= new HashMap<>();
        resultMap.put("name",redisUtil.get(paramMap.get("name").toString()));
//        model.addAttribute("reids",resultMap);
        return resultMap;
    }
    @RequestMapping("/setRedis")
    @ResponseBody
    public String setRedis(@RequestParam Map<String,Object> paramMap){
        String nameKey= paramMap.get("namekey").toString();
        String nameValue= paramMap.get("nameValue").toString();
        redisUtil.set(nameKey,nameValue);
        return "添加成功";
    }

    @RequestMapping("/getsql")
    @ResponseBody
    public Object getsql(@RequestParam Map<String,Object> paramMap){
//        String nameKey= paramMap.get("namekey").toString();
//        String nameValue= paramMap.get("nameValue").toString();
//        redisUtil.set(nameKey,nameValue);
        Long a= 16L;
        HashMap<String,Object> blog = blogService.getBlog1(a);
        System.out.println("a"+blog.get("kkkkk"));
        if (StringUtils.isEmpty(blog.get("kkkkk"))){
            System.out.println("123456479");
        }
        return blog.get("kkk");
    }

    @RequestMapping("/setmysql")
    @ResponseBody
    public String setmysql(@RequestParam JSONObject blog){
//        String nameKey= paramMap.get("namekey").toString();
//        String nameValue= paramMap.get("nameValue").toString();
//        redisUtil.set(nameKey,nameValue);
//        JSONObject jsonObject =new JSONObject();
////        Blog blog =new Blog();
//        JSONArray jsonArray =JSONArray.parseArray("[\"A\",\"A\",\"A\"]");
//        jsonObject.put("a",jsonArray);
//        blog.setId(16L);
//        blog.setTitle(jsonObject.get("a"));
//        blogService.updateBlog(blog);
//        Blog blog1 = blogService.getBlog(16L);
//        System.out.println(blogService.getBlog(16L));
        String title = blog.getString("title");
        return title;
    }

    public static void main(String[] args) {
        JSONArray jsonArray =JSONArray.parseArray("[\"A\",\"A\",\"A\"]");
        String string = jsonArray.toJSONString();
        System.out.println(jsonArray);
        System.out.println(string);
        HashMap<String,Object> n =new HashMap<>();
        n.put("a","[]");
        String a = "";
        if (StringUtils.isEmpty(n.get("a"))){
            System.out.println("aaaa");
        }else {
            JSONArray jsonArray1 = JSONArray.parseArray((String) n.get("a"));
//            n.put("a",jsonArray1);
            System.out.println(n);
        }

    }

}
