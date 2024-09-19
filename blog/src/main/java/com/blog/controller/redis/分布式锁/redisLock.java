package com.blog.controller.redis.分布式锁;

import com.blog.controller.redis.RedisUtil;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.UUID;

@Controller
@RequestMapping("/redis")
public class redisLock {
    @Resource
    private RedissonClient redissonClient;
    @Autowired
    RedisUtil redisUtil;

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/lock/test")
    @ResponseBody
    public void test() {
        String lockKey = "test_lock";
        RLock lock = redissonClient.getLock(lockKey);       //获取锁
        try {
            lock.lock();    //上锁
            log.info("锁已开启");
            synchronized (this){
                if(redisUtil.get("product")==null){
                    log.error("商品不存在！");
                }else{
                    //获取当前库存
                    int stock = Integer.parseInt(redisUtil.get("product").toString());
                    if (stock > 0){
                        int realStock = stock - 1;
                        //更新库存
                        redisUtil.set("product", realStock + "");
                        log.info("剩余当前为：" + realStock);
                    }else {
                        log.warn("扣减失败，库存不足！");
                    }
                }
            }
        }catch (Exception e){
            log.warn("系统错误，稍后重试");
        }
        finally {
            lock.unlock();    //删除锁
            log.info("锁已关闭");
        }
    }


    //高并发下会出现超买，没有加锁
    @RequestMapping("/reduct01")
    @ResponseBody
    public String reduct01(){
        Integer stock=Integer.parseInt(redisUtil.get("stock").toString());
        if (stock<=0){
            System.out.println("扣减失败，库存不足");
        }else{
            Integer realStock=stock-1;
            redisUtil.set("stock",realStock+"");
            System.out.println("扣减成功，剩余库存："+realStock);
        }
        return "end";
    }

    //高并发下单机模式不会出现超买，但是在分布式环境下，仍然会出现超买的现象
    @GetMapping("/reduct02")
    public String reduct02(){
        synchronized (this) {
            Integer stock = Integer.parseInt(redisUtil.get("stock").toString());
            if (stock < 0) {
                System.out.println("扣减失败，库存不足");
            } else {
                Integer realStock = stock - 1;
                redisUtil.set("stock", realStock + "");
                System.out.println("扣减成功，剩余库存：" + realStock);
            }
        }
        return "end";
    }



    //通过setnx key value命令，对商品库存进行加锁
    @GetMapping("/reduct03")
    public String reduct03(){

        //这里的key一般设置为和商品有关的key，以提高性能
        String lockKey="product_01";

        //这里可以将value设置为线程id
        String value= UUID.randomUUID().toString();

//        stringRedisTemplate.opsForValue().setIfAbsent(lockKey,"setnx"); -> setnx key value
//        stringRedisTemplate.expire(lockKey,10,TimeUnit.SECONDS);

        //设置setnx和超时时间要原子操作
        Boolean flag = redisUtil.setNx(lockKey, value, 10);

        if (!flag){
            //加锁失败
            return "error code";
        }

        /**
         * 因为在操作的过程中，可能会抛出异常，因此我们要用try将代码块包起来，然后在finally中，释放锁，否则会出现因为异常退出，但锁在redis中存在，从而
         * 使其他线程在访问该商品时加锁失败，无法顺利扣减库存
         */
        try {
            Integer stock = Integer.parseInt(redisUtil.get("stock").toString());
            if (stock < 0) {
                System.out.println("扣减失败，库存不足");
            } else {
                Integer realStock = stock - 1;
                redisUtil.set("stock", realStock + "");
                System.out.println("扣减成功，剩余库存：" + realStock);
            }
        }finally {
            //双重保证，将value设置为当前线程的id，保证锁不会被其他线程删掉
            if (value.equals(redisUtil.get(lockKey))) {
                //删除锁
                redisUtil.del(lockKey);
            }
        }
        return "end";
    }










    /**
     * 在上面的reduct03代码中，我们通过setnx key value进行加锁，我们设置的超时时间是10秒，假设当前商品库存为100，现在线程A访问该方法，获取到
     * 锁，然后在执行业务代码时，执行的时间超过了10秒，此时锁过期了，这时线程B访问该方法，获取到锁，获取商品库存为100，这样当A和B线程都执行完毕后，
     * 商品的库存实际上，只减少了1，也就是变成99，从而导致超买,那么我们需要在锁过期前，进行续期，因此使用redisson
     * @return
     */
    @GetMapping("/reduct04")
    public String reduct04(){
        String lockKey="product_01";

        RLock redissonLock = redissonClient.getLock(lockKey);

        try {
            redissonLock.lock();
            Integer stock = Integer.parseInt(redisUtil.get("stock").toString());
            if (stock < 0) {
                System.out.println("扣减失败，库存不足");
            } else {
                Integer realStock = stock - 1;
                redisUtil.set("stock", realStock + "");
                System.out.println("扣减成功，剩余库存：" + realStock);
            }
        }finally {
            redissonLock.unlock();
            //删除锁
            redisUtil.del(lockKey);
        }
        return "end";
    }
}
