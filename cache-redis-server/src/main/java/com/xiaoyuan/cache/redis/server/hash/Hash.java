package com.xiaoyuan.cache.redis.server.hash;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author liyuan
 * @description
 * @date 2019-12-30-10:17
 */
@RestController
public class Hash {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * hget key field
     * 获取存储在哈希表中指定字段的值
     *
     * @param key
     * @param field
     * @return
     */
    public Object hGet(String key, String field) {
        return stringRedisTemplate.opsForHash().get(key, field);
    }

    /**
     * hgetall key
     * 返回哈希表 key 中，所有的域和值。
     * 在返回值里，紧跟每个域名(field name)之后是域的值(value)，所以返回值的长度是
     * 哈希表大小的两倍。
     * <p>
     * 获取所有给定字段的值
     *
     * @param key
     * @return
     */
    public Map<Object, Object> hGetAll(String key) {
        return stringRedisTemplate.opsForHash().entries(key);
    }

    /**
     * hmget key field [field ...]
     * 返回哈希表 key 中，一个或多个给定域的值。
     * 如果给定的域不存在于哈希表，那么返回一个 nil 值。
     * 因为不存在的 key 被当作一个空哈希表来处理，所以对一个不存在的 key 进行 HMGET
     * 操作将返回一个只带有 nil 值的表
     * <p>
     * 获取所有给定字段的值
     *
     * @param key
     * @param fields
     * @return
     */
    public List<Object> hMultiGet(String key, Collection<Object> fields) {
        return stringRedisTemplate.opsForHash().multiGet(key, fields);
    }

    /**
     * key 中存fileid为hashKey 值value
     *
     * @param key
     * @param hashKey
     * @param value
     */
    public void hPut(String key, String hashKey, String value) {
        stringRedisTemplate.opsForHash().put(key, hashKey, value);
    }

    /**
     * 批量
     *
     * @param key
     * @param maps
     */
    public void hPutAll(String key, Map<String, String> maps) {
        stringRedisTemplate.opsForHash().putAll(key, maps);
    }

    /**
     * 仅当hashKey不存在时才设置
     *
     * @param key
     * @param hashKey
     * @param value
     * @return
     */
    public Boolean hPutIfAbsent(String key, String hashKey, String value) {
        return stringRedisTemplate.opsForHash().putIfAbsent(key, hashKey, value);
    }

    /**
     * hdel key field [field ...]
     * 删除哈希表 key 中的一个或多个指定域，不存在的域将被忽略。
     *
     * @param key
     * @param fields
     * @return
     */
    public Long hDelete(String key, Object... fields) {
        return stringRedisTemplate.opsForHash().delete(key, fields);
    }

    /**
     * hexists key field
     * 查看哈希表 key 中，指定的字段是否存在
     *
     * @param key
     * @param field
     * @return
     */
    public boolean hExists(String key, String field) {
        return stringRedisTemplate.opsForHash().hasKey(key, field);
    }

    /**
     * hincrby key field increment
     * 为哈希表 key 中的域 field 的值加上增量 increment 。
     * 增量也可以为负数，相当于对给定域进行减法操作。
     * 如果 key 不存在，一个新的哈希表被创建并执行 HINCRBY 命令。
     * 如果域 field 不存在，那么在执行命令前，域的值被初始化为 0 。
     * 对一个储存字符串值的域 field 执行 HINCRBY 命令将造成一个错误。
     * 本操作的值被限制在 64 位(bit)有符号数字表示之内
     * <p>
     * 为哈希表 key 中的指定字段的整数值加上增量 increment
     *
     * @param key
     * @param field
     * @param increment
     * @return
     */
    public Long hIncrBy(String key, Object field, long increment) {
        return stringRedisTemplate.opsForHash().increment(key, field, increment);
    }

    /**
     * 为哈希表 key 中的指定字段的整数值加上增量 increment
     *
     * @param key
     * @param field
     * @param delta
     * @return
     */
    public Double hIncrByFloat(String key, Object field, double delta) {
        return stringRedisTemplate.opsForHash().increment(key, field, delta);
    }

    /**
     * 获取所有哈希表中的字段
     *
     * @param key
     * @return
     */
    public Set<Object> hKeys(String key) {
        return stringRedisTemplate.opsForHash().keys(key);
    }

    /**
     * 获取hashKey的个数【hlen】
     * <p>
     * 获取哈希表中字段的数量
     *
     * @param key
     * @return
     */
    public Long hSize(String key) {
        return stringRedisTemplate.opsForHash().size(key);
    }

    /**
     * 返回查询键关联的值的长度，为null则返回0【hstrlen】
     *
     * @param key
     * @param hashKey
     */
    public Long hLengthOfValue(String key, String hashKey) {
        return stringRedisTemplate.opsForHash().lengthOfValue(key, hashKey);
    }

    /**
     * 返回哈希表 key 中所有域的值
     *
     * @param key
     * @return
     */
    public List<Object> hValues(String key) {
        return stringRedisTemplate.opsForHash().values(key);
    }

    /**
     * 迭代哈希表中的键值对
     *
     * @param key
     * @param options
     * @return
     */
    public Cursor<Map.Entry<Object, Object>> hScan(String key, ScanOptions options) {
        return stringRedisTemplate.opsForHash().scan(key, options);
    }


}
