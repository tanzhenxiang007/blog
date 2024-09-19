package com.blog;

import com.blog.pojo.BlogAndTag;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.admin4j.http.core.Pair;
import io.github.admin4j.http.util.HttpUtil;
import okhttp3.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.io.IOException;

@SpringBootTest
public class redisTest {
    @Autowired
    private StringRedisTemplate redisTemplate;

    // JSON工具
    private static final ObjectMapper mapper = new ObjectMapper();

    @Test
    void testOne() {
        redisTemplate.opsForValue().set("名称2", "谭振祥2");
        String personJson = redisTemplate.opsForValue().get("名称2");
        System.out.println(personJson);
    }
    @Test
    void testTwo() throws IOException {
        Person person = new Person("我是一棵卷心菜", 30);
        //  手动序列化
        String json = mapper.writeValueAsString(person);
        redisTemplate.opsForValue().set("person", json);
        String personJson = redisTemplate.opsForValue().get("person");
        // 反序列化
        Person person1 = mapper.readValue(personJson, Person.class);
        System.out.println(person1);

    }
    @Test
    public void getHttp(){
        Response response = HttpUtil.get("https://www.baidu.com/s", Pair.of("wd", "123"));
        System.out.println("response = " + response);
        System.out.println("response.body().toString() = " + response.body().toString());
    }


}
