package com.xiaoyuan.cache.redis.server.test;


import org.redisson.RedissonRedLock;
import org.redisson.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.security.SecureRandom;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author liyuan
 * @create 2020-03-12-19:09
 */
@RestController
public class RedisTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private Jedis jedis;
    @Autowired
    private RedissonClient redissonClient;

    @RequestMapping("/test")
    public String test() {
        String lockKey = "asdasdasd";


        // -------------------------------------------------
/*        String clientId = UUID.randomUUID().toString();
        Boolean success = redisTemplate.opsForValue().setIfAbsent(lockKey, clientId, 10, TimeUnit.SECONDS);
        if (!success) {
            return "error";
        }*/
        // -------------------------------------------------


        RLock redissonLock = redissonClient.getLock(lockKey);
        try {
            redissonLock.lock();
            int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock").toString());
            if (stock > 0) {
                int realStock = stock - 1;
                stringRedisTemplate.opsForValue().decrement("stock");
                System.out.println("扣减成功");
            } else {
                System.out.println("失败");
            }
        } finally {
            // -------------------------------------------------
/*            if (clientId.equals(redisTemplate.opsForValue().get(lockKey))) {
                redisTemplate.delete(lockKey);
            }*/
            // -------------------------------------------------

            redissonLock.unlock();

        }
        return "end";
    }

    @RequestMapping("/redlock")
    public String redlock() {
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
            }
        } catch (InterruptedException e) {
            System.out.println("所失败");
        } finally {
            // 释放锁
            redissonRedLock.unlock();
        }
        return "success";
    }

    /**
     * 抽奖
     *
     * @param args
     */
    public String lottery(String[] args) {
//        普通抽奖
        String[] qq = {"1", "2", "3", "4", "5", "6"};
        Integer num = 2;
        Set<String> set = new HashSet<>();
        for (; ; ) {
            if (set.size() == num) {
                break;
            }
            int random = new SecureRandom().nextInt(qq.length);
            set.add(qq[random]);
        }
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        String key = "lottery";
        // 一等奖
        List<String> firstList = stringRedisTemplate.opsForSet().pop(key, 1);
        // 二等奖
        List<String> secondList = stringRedisTemplate.opsForSet().pop(key, 3);
        // 三等奖
        List<String> thirdList = stringRedisTemplate.opsForSet().pop(key, 5);
        return "success";
    }

    /**
     * 添加拼团(只能自己报名)
     *
     * @param groupNumber
     * @param id
     * @return
     */
    public String group(String groupNumber, String id) {
        Long size = stringRedisTemplate.opsForList().size(groupNumber);
        String lockKey = "group";
        if (size < 10) {
            RLock redissonLock = redissonClient.getLock(lockKey);
            try {
                redissonLock.lock();
                if (stringRedisTemplate.opsForList().size(groupNumber) < 10) {
                    stringRedisTemplate.opsForList().rightPush(groupNumber, id);
                    return "success";
                }
            } finally {
                redissonLock.unlock();
            }
            return "人数已满";
        } else {
            return "人数已满";
        }
    }

    /**
     * 延时队列(sortedset)生产
     *
     * @param orderNumber 订单编码
     * @param endTime     截止时间
     * @return
     */
    public String orderDelayQueue(String orderNumber, Date endTime) {
        String key = "order";
        stringRedisTemplate.opsForZSet().add(key, orderNumber, endTime.getTime());
        return "success";
    }

    /**
     * 延时队列(sortedset)消费
     *
     * @return
     */
    public String orderDelayQueue() {
        long currentTimeMillis = System.currentTimeMillis();
        String key = "order";
        Set<String> set = stringRedisTemplate.opsForZSet().range(key, 0, currentTimeMillis);
        if (set.size() > 0) {
            stringRedisTemplate.opsForZSet().removeRange(key, 0, currentTimeMillis);
            set.forEach(i -> System.out.println("改状态:" + i));
        }
        return "success";
    }

    /**
     * pipeline(管道),大批量操作redis,前后必须无因果关系
     *
     * @return
     */

    public String pipelineTest() {

        // 一大堆商品补充库存了
        List<Integer> ids = Arrays.asList(1, 2, 3);
        //连接redis
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        //获取pipeline
        Pipeline pipeline = jedis.pipelined();
        ids.forEach(i -> {
            pipeline.lpush(i.toString(), i.toString());
            pipeline.incrBy(i.toString(), 1000);
        });
        pipeline.sync();
        //关闭
        pipeline.close();
        jedis.close();


        // 使用 RedisCallback 把命令放在 pipeline 中
        RedisCallback<Object> redisCallback = connection -> {

            StringRedisConnection stringRedisConnection = (StringRedisConnection) connection;
            for (int i = 0; i != 10; ++i) {
                stringRedisConnection.set(String.valueOf(i), String.valueOf(i));
            }

            return null;    // 这里必须要返回 null
        };
        stringRedisTemplate.executePipelined(redisCallback);


        // 使用 SessionCallback 把命令放在 pipeline
        SessionCallback<Object> sessionCallback = new SessionCallback<Object>() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {

                operations.opsForValue().set("name", "qinyi");
                operations.opsForValue().set("gender", "male");
                operations.opsForValue().set("age", "19");

                return null;
            }
        };
        stringRedisTemplate.executePipelined(sessionCallback);

        return "success";
    }


    /**
     * 布隆过滤器
     */
    public RBloomFilter<Object> bloomFilter(String s) {
        RBloomFilter<Object> bloomFilter = redissonClient.getBloomFilter(s);
        bloomFilter.tryInit(1000, 0.03);

        for (int i = 0; i < 1000; i++) {
            bloomFilter.add("瓜田李下 " + i);
        }

        System.out.println("'瓜田李下 1'是否存在：" + bloomFilter.contains("瓜田李下 " + 1));
        System.out.println("'海贼王'是否存在：" + bloomFilter.contains("海贼王"));
        System.out.println("预计插入数量：" + bloomFilter.getExpectedInsertions());
        System.out.println("容错率：" + bloomFilter.getFalseProbability());
        System.out.println("hash函数的个数：" + bloomFilter.getHashIterations());
        System.out.println("插入对象的个数：" + bloomFilter.count());
        return bloomFilter;
    }

    /**
     * redis限流
     *
     * @return
     */
    public String traffic() {
        RSemaphore traffic = redissonClient.getSemaphore("traffic");
        boolean b = traffic.tryAcquire();
        if (b) {
            // 获取到服务执行业务
            System.out.println("");
        }
        return "服务正忙";

    }

    /**
     * 流量释放
     *
     * @return
     */
    public String release() {
        RSemaphore traffic = redissonClient.getSemaphore("traffic");
        traffic.release();
        return "end";
    }


    /**
     * 闭锁
     *
     * @return
     * @throws InterruptedException
     */
    public String lockDoor() throws InterruptedException {
        RCountDownLatch countDownLatch = redissonClient.getCountDownLatch("");
        countDownLatch.trySetCount(5);
        countDownLatch.await();
        return "执行操作";
    }

    public String gogogo() {
        RCountDownLatch countDownLatch = redissonClient.getCountDownLatch("");
        // 自减
        countDownLatch.countDown();
        return "";
    }

}
