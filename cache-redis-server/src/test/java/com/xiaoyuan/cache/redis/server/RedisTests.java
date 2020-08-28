package com.xiaoyuan.cache.redis.server;

import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

/**
 * @author : liyuan  
 * @description :
 * @date : 2020/8/10 10:23  
 */
@SpringBootTest
public class RedisTests {


    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedissonClient redissonClient;

    @Test
    public void init() {
        String s = stringRedisTemplate.opsForValue().get("goods_id:401");
        System.out.println(JSONObject.toJSONString(s));
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(s));
    }
}
