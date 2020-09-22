package com.xiaoyuan.cache.redis.server;

import org.junit.jupiter.api.Test;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

/**
 * @author : liyuan  
 * @description :
 * @date : 2020/9/15 9:48  
 */
@SpringBootTest
public class ZSetTests {

    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedissonClient redissonClient;


    @Test
    void init(){
        Double score = stringRedisTemplate.opsForZSet().score("LIVE_ABNORMAL_EXIT", "liveCode");
        System.out.println();
    }
}
