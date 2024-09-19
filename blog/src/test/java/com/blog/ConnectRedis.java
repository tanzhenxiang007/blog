package com.blog;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/*Jedis是Redis官方推荐的Java连接开发工具！ 虽然现在的SpringBoot2.×版本已经将Jedis换成了Lettuce*/
public class ConnectRedis {
    public static void main(String[] args) {
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        // 如果 Redis 服务设置了密码，需要用下面这行代码输入密码
//                jedis.auth("P@ssword1");
        System.out.println("连接成功");
        //查看服务是否运行
        System.out.println("服务正在运行: " + jedis.ping());
        //清空当前库的所有数据
//                jedis.flushDB();

        jedis.set("name1","dingyongjun");
        jedis.set("age1","23");
        jedis.set("high1","173");
        System.out.println("name:"+jedis.get("name")+"\nage:"+jedis.get("age")+"\nhigh"+jedis.get("high"));


        jedis.lpush("list1", "1", "2", "3", "4");
        System.out.println("list: " + jedis.lrange("list", 0, -1));
    }
}
