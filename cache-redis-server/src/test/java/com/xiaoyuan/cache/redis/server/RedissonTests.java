package com.xiaoyuan.cache.redis.server;

import org.junit.jupiter.api.Test;
import org.redisson.api.RCountDownLatch;
import org.redisson.api.RSemaphore;
import org.redisson.api.RedissonClient;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author : liyuan  
 * @description :
 * @date : 2020-01-09 0:23  
 */
@SpringBootTest
public class RedissonTests {

    @Resource
    private RedissonClient redissonClient;


    /**
     * @description redis限流
     */
    @Test
    public void traffic() {
        RSemaphore traffic = redissonClient.getSemaphore("traffic");
        boolean b = traffic.tryAcquire();
        if (b) {
            System.out.println("获取到服务执行业务");
        }
        System.out.println("服务正忙");
    }

    /**
     * @description 流量释放
     */
    @Test
    public void release() {
        RSemaphore traffic = redissonClient.getSemaphore("traffic");
        traffic.release();
        System.out.println("方法执行结束,连接池候选位置释放");
    }


    /**
     * @throws InterruptedException
     * @description 闭锁
     */
    @Test
    public void lockDoor() throws InterruptedException {
        RCountDownLatch countDownLatch = redissonClient.getCountDownLatch("group");
        countDownLatch.trySetCount(5);
        System.out.println("等待上面限制的几人开始执行");
        countDownLatch.await();
        System.out.println("执行操作");
    }

    /**
     * @description 闭锁执行操作
     */
    @Test
    public void Share() {
        RCountDownLatch countDownLatch = redissonClient.getCountDownLatch("group");
        countDownLatch.countDown();
        System.out.println("加入拼团,团队位置减一");
    }


}
