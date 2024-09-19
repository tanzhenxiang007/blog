package com.blog.controller.test;


import com.blog.study.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
@RequestMapping("/test")
public class testController {


    @RequestMapping("/user")
    public JsonResult getUser() {
        return  JsonResult.ok();
    }

//    @RequestMapping("/list")
//    public JsonResult<List> getUserList() {
//        List<User2> userList = new ArrayList<>();
//        User2 user1 = new User2(1, "倪升武", "123456");
//        User2 user2 = new User2(2, "达人课", "123456");
//        userList.add(user1);
//        userList.add(user2);
//        return new JsonResult<>(userList, "获取用户列表成功");
//    }
//
//    @RequestMapping("/map")
//    public JsonResult<Map> getMap() {
//        Map<String, Object> map = new HashMap<>(3);
//        User2 user = new User2(1, "倪升武", null);
//        map.put("作者信息", user);
//        map.put("博客地址", "http://blog.itcodai.com");
//        map.put("CSDN地址", null);
//        map.put("粉丝数量", 4153);
//        return new JsonResult<>(map);
//    }





}
