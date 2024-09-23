package com.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

//@ComponentScan(basePackages = {"com.blog.util"})
//@SpringBootApplication
@EnableScheduling //这个注解的作用就是开启定时任务功能 利用springtask即时spring的自己的技术
@ServletComponentScan //用于自动扫描和注册使用@WebServlet, @WebFilter, 或 @WebListener注解的类
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class}) //移除默认数据库配置类
public class BlogApplication {

	public static void main(String[] args) {
        //启动springboot的应用 返回的是springboot的ioc容器
		ConfigurableApplicationContext context = SpringApplication.run(BlogApplication.class, args);


        //获取user Bean   检验类是否存在
        Object user = context.getBean("user");
        System.out.println(user);
    }

}
