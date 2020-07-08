package com.xiaoyuan.cache.redis.server;

import org.junit.jupiter.api.Test;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author : liyuan  
 * @description : redis用bitmap位图实现布隆过滤器
 * @date : 2020-02-21-14:23 
 */
@SpringBootTest
public class BloomFilterTests {

    @Resource
    private RedissonClient redissonClient;

    @Test
    public void init() {
        String message = "";
        RBloomFilter<Object> bloomFilter = redissonClient.getBloomFilter(message);
        bloomFilter.tryInit(1000, 0.03);

        for (int i = 0; i < 1000; i++) {
            bloomFilter.add("测试布隆过滤器--- " + i);
        }
        System.out.println("'测试布隆过滤器--- 1'是否存在：" + bloomFilter.contains("测试布隆过滤器--- " + 1));
        System.out.println("'我肯定不在里面'是否存在：" + bloomFilter.contains("我肯定不在里面"));
        System.out.println("预计插入数量：" + bloomFilter.getExpectedInsertions());
        System.out.println("容错率：" + bloomFilter.getFalseProbability());
        System.out.println("hash函数的个数：" + bloomFilter.getHashIterations());
        System.out.println("插入对象的个数：" + bloomFilter.count());

        if (!bloomFilter.contains("某个商品id")) {
            System.out.println("异常请求!!!!");
        }
    }
}
