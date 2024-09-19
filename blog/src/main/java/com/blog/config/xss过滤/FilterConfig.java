//package com.blog.config.xss过滤;
//
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.servlet.DispatcherType;
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//public class FilterConfig {
//
//    @Value("${xss.excludes}")
//    private String excludes;
//
//    @Value("${xss.urlPatterns}")
//    private String urlPatterns;
//
//    @Bean
//    @ConditionalOnProperty(value = "xss.enabled",havingValue = "true") //条件属性，获取值，里面有“true”怎生效
//    public FilterRegistrationBean xssFilterRegistration(){
//        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//        //设置dispatcher类型
//        registrationBean.setDispatcherTypes(DispatcherType.REQUEST);
//        //设置过滤器
//        registrationBean.setFilter(new XssFilter());
//        //增加匹配访问链接
//        registrationBean.addUrlPatterns(StringUtils.split(urlPatterns,","));
//        //设置过滤器名称
//        registrationBean.setName("xssFilter");
//        //设置过滤器优先级(xss过滤器为最高)
//        registrationBean.setOrder(FilterRegistrationBean.HIGHEST_PRECEDENCE);
//
//        //设置初始化参数
//        Map<String,String> initParameters = new HashMap<String,String>();
//        initParameters.put("excludes",excludes);
//        registrationBean.setInitParameters(initParameters);
//
//        return registrationBean;
//    }
//}
//
