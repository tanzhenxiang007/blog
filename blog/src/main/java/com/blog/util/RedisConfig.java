package com.blog.util;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.redisson.Redisson;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {


    /**
     * 对列名称
     */
    private final String queueName = "orderQueue";


    // 这是我给大家写好的一个固定模板，大家在企业中，拿去就可以直接使用！
    // 自己定义了一个RedisTemplate
    @Bean
    @SuppressWarnings("all")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        // 我们为了自己开发方便，一般直接使用 <String, Object>
        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(factory);
        // Json序列化配置
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        // String 的序列化
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        // key采用String的序列化方式
        template.setKeySerializer(stringRedisSerializer);
        // hash的key也采用String的序列化方式
        template.setHashKeySerializer(stringRedisSerializer);
        // value序列化方式采用jackson
        template.setValueSerializer(jackson2JsonRedisSerializer);
        // hash的value序列化方式采用jackson
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }

    /**
     * 初始化一个Redis消息监听容器
     * redis订阅消息的监听容器RedisMessageListenerContainer
     * @param connectionFactory
     * @return
     */
    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory connectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        // 添加其他配置，如线程池大小等
        return container;
    }



        //创建一个Redisson客户端实例
    @Bean(destroyMethod = "shutdown")
    //@Bean注解中的destroyMethod = "shutdown"属性指定了当Spring容器关闭时，
    // 应该调用RedissonClient的shutdown方法。这是为了确保在应用程序关闭时，Redisson客户端能够释放资源和关闭与Redis服务器的连接。
    public RedissonClient redissonClient() {
        Config config = new Config();
        //公司redis环境
        config.useSingleServer().setAddress("redis://172.16.91.27:6379").setPassword("yunqu168");
        return Redisson.create(config);
    }


    //阻塞队列
    @Bean//这部分代码创建了一个RBlockingQueue的实例，并将其作为Spring容器中的一个bean管理。
    public RBlockingQueue<String> blockingQueue(RedissonClient redissonClient) {
        return redissonClient.getBlockingQueue(queueName);
    }


    //延迟队列
    @Bean
    public RDelayedQueue<String> delayedQueue(RedissonClient redissonClient, RBlockingQueue<String> blockQueue) {
        return redissonClient.getDelayedQueue(blockQueue);

    }
}
