package com.xiaoyuan.cache.redis.server;

import org.junit.jupiter.api.Test;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

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


        String script = "if (redis.call('exists', KEYS[1]) == 1) then " +
                " local count = redis.call('get', KEYS[1]) " +
                " local a = tonumber(count) " +
                " local b = tonumber(ARGV[1]) " +
                " if a >= b then " +
                " redis.call('decrby', KEYS[1], b) " +
                " return 1 " +
                " end " +
                " return 0 " +
                " end " +
                " return 0 ";

        DefaultRedisScript<List> defaultRedisScript = new DefaultRedisScript<>();
        defaultRedisScript.setResultType(List.class);
        defaultRedisScript.setScriptText(script);

        List success = stringRedisTemplate.execute(defaultRedisScript, Collections.singletonList("LADDER_PRODUCT_SKU_STOCK:4_11_23"), 2 + "");
        System.out.println(success);
    }

    /**
     * @param lockKey    锁对象
     * @param uuid       uuid或者带时间戳的字符串
     * @param expireTime 过期时间 单位秒
     * @return
     * @description lua脚本-->>分布式锁
     */
    public boolean distributedLock(String lockKey, String uuid, long expireTime) {
        if (StringUtils.isEmpty(lockKey)) {
            return false;
        }
        if (StringUtils.isEmpty(uuid)) {
            return false;
        }
        if (expireTime < 1L) {
            return false;
        }
        String scriptLock = "if (redis.call('exists', KEYS[1]) == 0) " +
                "then redis.call('set', KEYS[1], ARGV[1]) redis.call('expire', KEYS[1], ARGV[2]);" +
                " return 1; " +
                "end; " +
                "return 0; ";
        DefaultRedisScript<Long> defaultRedisScript = new DefaultRedisScript<>();
        defaultRedisScript.setResultType(Long.class);
        defaultRedisScript.setScriptText(scriptLock);
        Long success = stringRedisTemplate.execute(defaultRedisScript, Collections.singletonList(lockKey), uuid, expireTime + "");
        return success != null && success == 1;
    }

    /**
     * @param lockKey      锁对象
     * @param lockKeyValue 锁对象 值
     * @return
     * @description lua脚本-->>分布式锁 释放
     */
    public boolean distributedUnLock(String lockKey, String lockKeyValue) {
        if (StringUtils.isEmpty(lockKey)) {
            return false;
        }
        if (StringUtils.isEmpty(lockKeyValue)) {
            return false;
        }
        String scriptLock = "if (redis.call('get', KEYS[1]) == ARGV[1]) " +
                "then redis.call('del', KEYS[1]) ;" +
                " return 1; " +
                "end; " +
                "return 0; ";
        DefaultRedisScript<Long> defaultRedisScript = new DefaultRedisScript<>();
        defaultRedisScript.setResultType(Long.class);
        defaultRedisScript.setScriptText(scriptLock);
        Long success = stringRedisTemplate.execute(defaultRedisScript, Collections.singletonList(lockKey), lockKeyValue);
        return success != null && success == 1;
    }

}
