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
 * @date : 2020/8/6 13:24  
 */
@SpringBootTest
public class StreamTests {

    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedissonClient redissonClient;

    @Test
    public void init(){


    }

}
