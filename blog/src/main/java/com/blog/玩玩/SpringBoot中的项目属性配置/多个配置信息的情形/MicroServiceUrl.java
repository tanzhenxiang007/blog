package com.blog.玩玩.SpringBoot中的项目属性配置.多个配置信息的情形;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "url")
@Data
public class MicroServiceUrl {
//使用 @ConfigurationProperties 注解并且使用 prefix 来指定一个前缀，然后该类中的属性名就是配置中去掉前缀后的名字，
// 一一对应即可。即：前缀名 + 属性名就是配置文件中定义的 key。
// 同时，该类上面需要加上 @Component 注解，把该类作为组件放到Spring容器中，让 Spring 去管理，我们使用的时候直接注入即可

    private String orderUrl;
    private String userUrl;
    private String shoppingUrl;

}
