package com.xiaoyuan.cache.redis.server.hyperLogLog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HyperLogLogOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liyuan
 * @create 2020-01-02-0:26
 */
@RestController
@RequestMapping("/hyperLogLog")
public class HyperLogLog {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 大数据量统计  误差:0.81%
     */

    /**
     * 往key添加值
     * @param key
     * @param values
     */
    public void pfAdd(String key, String... values) {
        HyperLogLogOperations<String, String> hyperLogLogOperations = stringRedisTemplate.opsForHyperLogLog();
        hyperLogLogOperations.add(key,values);

    }

    /**
     * keys 总共出现的次数
     * @param keys
     * @return
     */
    public Long pfCount(String... keys){
        HyperLogLogOperations<String, String> hyperLogLogOperations = stringRedisTemplate.opsForHyperLogLog();
        return hyperLogLogOperations.size(keys);
    }

    /**
     * 将多个 HyperLogLog 合并为一个 HyperLogLog
     * @param key
     * @param keys
     * @return
     */
    public Long pfCount(String key,String... keys){
        HyperLogLogOperations<String, String> hyperLogLogOperations = stringRedisTemplate.opsForHyperLogLog();
        return hyperLogLogOperations.union(key, keys);
    }
}
