package com.xiaoyuan.cache.redis.server;

import org.junit.jupiter.api.Test;
import org.redisson.api.RedissonClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : liyuan  
 * @description : redis 管道技术 将多个redis操作命令同时执行,最后一起返回结果,但是每个命令之间不能有因果关系;减少网络io
 * @date : 2020-01-31-23:17
 */
@SpringBootTest
public class PipelinedTests {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedissonClient redissonClient;

    /**
     * @description pipeline(管道),大批量操作redis,前后必须无因果关系
     *
     * @return
     */
    @Test
    public void init() {


        // 使用 RedisCallback 把命令放在 pipeline 中
        RedisCallback<Object> redisCallback = connection -> {

            StringRedisConnection stringRedisConnection = (StringRedisConnection) connection;
            for (int i = 0; i != 10; ++i) {
                stringRedisConnection.set(String.valueOf(i), String.valueOf(i));
            }

            return null;    // 这里必须要返回 null
        };
        List<Object> objects = stringRedisTemplate.executePipelined(redisCallback);
        objects.forEach(System.out::println);

        // 使用 SessionCallback 把命令放在 pipeline
        SessionCallback<Object> sessionCallback = new SessionCallback<Object>() {
            @Override
            public Object execute(RedisOperations operations){
                operations.opsForValue().set("name", "qinyi");
                operations.opsForValue().set("gender", "male");
                operations.opsForValue().set("age", "19");
                return null;
            }
        };

        List<Object> objects1 = stringRedisTemplate.executePipelined(sessionCallback);
        System.out.println("如果是get取值的命令就遍历");
        objects1.forEach(System.out::println);
    }
}
