package com.blog.玩玩.监听器.监听HTTP会话Session对象.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ResourceBundle;

@RestController
@RequestMapping("/listener")
@Slf4j
public class TestController5 {

    /**
     * 获取当前在线人数，该方法有bug
     *
     *
     * 该 Controller 中是直接获取当前 session 中的用户数量，启动服务器，在浏览器中输入 localhost:8080/listener/total
     * 可以看到返回的结果是1，再打开一个浏览器，请求相同的地址可以看到 count 是 2 ，
     * 这没有问题。但是如果关闭一个浏览器再打开，理论上应该还是2，但是实际测试却是 3。
     * 原因是 session 销毁的方法没有执行（可以在后台控制台观察日志打印情况），
     * 当重新打开时，服务器找不到用户原来的 session，于是又重新创建了一个 session
     * @param request
     * @return
     */
    @GetMapping("/total")
    public String getTotalUser(HttpServletRequest request) {
        Integer count = (Integer) request.getSession().getServletContext().getAttribute("count");
        return "当前在线人数：" + count;
    }


    @GetMapping("/total2")
    public String getTotalUser(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie;
        try {
            // 把sessionId记录在浏览器中
            cookie = new Cookie("JSESSIONID", URLEncoder.encode(request.getSession().getId(), "utf-8"));
            cookie.setPath("/");
            //设置cookie有效期为2天，设置长一点
            cookie.setMaxAge( 48*60 * 60);
            //该处理逻辑是让服务器记得原来那个 session，即把原来的 sessionId 记录在浏览器中，
            // 下次再打开时，把这个 sessionId 传过去，这样服务器就不会重新再创建了
            //todo 请求先进来这里 然后再跳转到监控session那里
            response.addCookie(cookie);
            // 获取所有的cookie
//            Cookie[] cookies = request.getCookies();
//            for (Cookie cookie1 : cookies) {
//                String value = cookie1.getValue();
//                log.info("value=="+value);
//            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Integer count = (Integer) request.getSession().getServletContext().getAttribute("count");
        return "当前在线人数：" + count;
    }

    /**
     *    特定日期+天数是否大于小于当天
     */
//    public static void main(String[] args) {
//        // 给定的日期字符串和天数
//        String time = "2024-09-06";
//        int date = 5;
//
//        // 将字符串日期转换为 LocalDate 对象
//        LocalDate givenDate = LocalDate.parse(time, DateTimeFormatter.ISO_LOCAL_DATE);
//
//        // 给定日期加上指定的天数
//        LocalDate newDate = givenDate.plusDays(date);
//
//        // 获取今天的日期
//        LocalDate today = LocalDate.now();
//
//        // 比较两个日期
//        if (newDate.isBefore(today)) {
//            System.out.println("给定日期加上" + date + "天后小于今天的日期。");
//        } else {
//            System.out.println("给定日期加上" + date + "天后大于或等于今天的日期。");
//        }
//    }

    public static void main(String[] args) {

        String value = ResourceBundle.getBundle("operForEveryMonth").getString("admin");

        System.out.println(value);
//
//        String path = TestController5.class.getResource("/").getPath();
//
//        System.out.println(path);

        // 安全地拼接路径
//        String filePath = path + "operForEveryMonth.sql";

        // 使用 try-with-resources 确保资源自动关闭
//        try ( InputStream iStream = TestController5.class.getResourceAsStream("/operForEveryMonth.sql");) {
//            Properties properties = new Properties();
//
//            // 加载属性文件
//            properties.load(iStream);
//            System.out.println(properties.getProperty("className"));
//        } catch (IOException e) {
//            // 处理文件读取异常
//            System.err.println("Error loading properties file: " + e.getMessage());
//        }
    }

}