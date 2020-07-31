package com.xiaoyuan.cache.redis.server;

import org.junit.jupiter.api.Test;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collections;

/**
 * @author : liyuan  
 * @description :
 * @date : 2020/7/29 14:27  
 */
@SpringBootTest
public class LuaTests {

    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedissonClient redissonClient;


    /**
     * @description 使用lua脚本
     */
    @Test
    public void goToLockWithScript() {


        String script = " local count = redis.call('get', KEYS[1]) " +
                " local a = tonumber(count) " +
                " local b = tonumber(ARGV[1]) " +
                " if a >= b then " +
                " redis.call('set', KEYS[1], count‐b) " +
                " return 1 " +
                " end " +
                " return 0 ";

        DefaultRedisScript<String> defaultRedisScript = new DefaultRedisScript<>();
        defaultRedisScript.setResultType(String.class);
        defaultRedisScript.setScriptText(script);

        String success = stringRedisTemplate.execute(defaultRedisScript, Collections.singletonList("LIVE_VIEWS_TIMES:LV20071500001"), Arrays.asList(1));
        System.out.println(success);
    }



}
