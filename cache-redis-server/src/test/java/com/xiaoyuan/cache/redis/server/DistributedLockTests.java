package com.xiaoyuan.cache.redis.server;

import org.junit.jupiter.api.Test;
import org.redisson.RedissonRedLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author liyuan
 * @description 基于redis实现各种分布式
 * @date  2020-01-29-23:17
 */
@SpringBootTest
public class DistributedLockTests {

    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedissonClient redissonClient;

    /**
     * @description 使用stringRedisTemplate实现分布式锁  注意:锁释放时,是否还是这把锁,锁的时间是否能完成,锁的续命操作,锁续命需要开子线程去定时任务执行
     */
    @Test
    public void stringRedisTemplateLock() {
        String lockKey = "meToLock";

        String clientId = UUID.randomUUID().toString();
        Boolean success = stringRedisTemplate.opsForValue().setIfAbsent(lockKey, clientId, 30, TimeUnit.SECONDS);
        if (!success) {
            System.out.println("正在被占用");
        }
        try {
            int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
            if (stock > 0) {
                int realStock = stock - 1;
                stringRedisTemplate.opsForValue().decrement("stock");
                System.out.println("扣减成功，剩余库存:" + realStock);
            } else {
                System.out.println("失败");
            }
        } finally {
            if (clientId.equals(stringRedisTemplate.opsForValue().get(lockKey))) {
                stringRedisTemplate.delete(lockKey);
            }
        }
    }

    /**
     * @description 使用redisson实现分布式锁  内部底层自实现加锁 续命功能,默认30秒
     */
    @Test
    public void redissonClientLock(){

        String lockKey = "lockClientId_37";

        RLock redissonLock = redissonClient.getLock(lockKey);
        try {
            redissonLock.lock();
            int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("LIVE_VIEWS_TIMES:LV20071500001"));
            if (stock > 0) {
                int realStock = stock - 1;
                stringRedisTemplate.opsForValue().decrement("LIVE_VIEWS_TIMES:LV20071500001");
                System.out.println("扣减成功，剩余库存:" + realStock);
            } else {
                System.out.println("失败");
            }
        } finally {
            // 释放锁
            redissonLock.unlock();
        }
    }

    /**
     * @description 如果redis主从同步未来得及主服务器挂了,锁会丢失,几率很低;采用redlock红锁,主节点超过半数被锁上判定所成功,但是无法设定超时时间!!!;类似zk的分布式锁机制
     */
    @Test
    public void redLock() {
        String lockKey = "lock";
        // 这里需要自己实例化不同redis实例的redisson客户端连接们这里只是伪代码用一个redisson客户端简化了
        RLock lock1 = redissonClient.getLock(lockKey);
        RLock lock2 = redissonClient.getLock(lockKey);
        RLock lock3 = redissonClient.getLock(lockKey);
        RedissonRedLock redissonRedLock = new RedissonRedLock(lock1, lock2, lock3);
        try {
            /**
             * waitime 尝试获取锁的最大等待时间,超过则失败
             * leaseTime 锁的持有时间,超过时间锁会失效,(值应设置为大于业务处理的时间)
             */
            boolean success = redissonRedLock.tryLock(10, 30, TimeUnit.SECONDS);
            if (success) {
                // 获得锁
                int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
                if (stock > 0) {
                    int realStock = stock - 1;
                    stringRedisTemplate.opsForValue().decrement("stock");
                    System.out.println("扣减成功，剩余库存:" + realStock);
                } else {
                    System.out.println("失败");
                }
            }
        } catch (InterruptedException e) {
            System.out.println("所失败");
        } finally {
            redissonRedLock.unlock();
        }
    }

    /**
     * @description 使用lua脚本实现分布式锁...只是实现加锁和设置过期时间的原子性
     */
    @Test
    public void goToLockWithScript() {



        String lockKey = "product_info_992863";
        String clientId = UUID.randomUUID().toString();
        stringRedisTemplate.opsForValue().set(lockKey, "100");

        //lua分布式锁
        String scriptLock = "if (redis.call('hexists', KEYS[1], ARGV[1]) == 0) then redis.call('set', KEYS[1], ARGV[1]) redis.call('pexpire', KEYS[1], ARGV[2]); return 1; end; return 0;";

        DefaultRedisScript<String> defaultRedisScript = new DefaultRedisScript<>();
        defaultRedisScript.setResultType(String.class);
        defaultRedisScript.setScriptText(scriptLock);

        String success = stringRedisTemplate.execute(defaultRedisScript, Collections.singletonList(lockKey), Arrays.asList(clientId,1000));
        if("1".equals(success)){
            System.out.println("上锁成功");
        }


        // lua 扣减库存
        String script = " local count = redis.call('get', KEYS[1]) " +
                " local a = tonumber(count) " +
                " local b = tonumber(ARGV[1]) " +
                " if a >= b then " +
                " redis.call('set', KEYS[1], count‐b) " +
                " return 1 " +
                " end " +
                " return 0 ";

        defaultRedisScript = new DefaultRedisScript<>();
        defaultRedisScript.setResultType(String.class);
        defaultRedisScript.setScriptText(script);

        String execute = stringRedisTemplate.execute(defaultRedisScript, Collections.singletonList(lockKey), Collections.singletonList(10));
        if("1".equals(execute)){
            System.out.println("执行成功");
        }

    }
}
