package com.xiaoyuan.cache.redis.server.key;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author liyuan
 * @description
 * @date  2019-12-30-10:10
 */
@RestController
@RequestMapping("/key")
public class Key {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * @param key
     * @return
     * @description 提供了对key的“bound”(绑定)便捷化操作API，可以通过bound封装指定的key，然后进行一系列的操作而无须“显式”的再次指定Key，即BoundKeyOperations
     * 我对一个key要进行多次操作,不需要多次代码进行指定这个key了
     */
    public BoundValueOperations<String, String> boundValueOps(String key) {
        return stringRedisTemplate.boundValueOps(key);
    }

    /**
     * @param key
     * @return 是否删除成功
     * @description 删除key 不存在则也返回false
     */
    public Boolean delete(String key) {
        return stringRedisTemplate.delete(key);
    }

    /**
     * @param keys key集合
     * @return 被删除的个数
     * @description 批量删除key 不存在将被跳过,只返回被删除的key总数,如果都没有则返回0
     */
    public Long delete(Collection<String> keys) {
        return stringRedisTemplate.delete(keys);
    }

    /**
     * @param key
     * @return
     * @description 序列化key 不存在和返回null
     */
    public byte[] dump(String key) {
        return stringRedisTemplate.dump(key);
    }

    /**
     * @param key
     * @return
     * @description 是否存在key ,存在返回true,不存在返回false
     */
    public Boolean hasKey(String key) {
        return stringRedisTemplate.hasKey(key);
    }

    /**
     * @param key     需要设置过期时间的键
     * @param timeout 距离多久过期
     * @param unit    时间单位
     * @return 是否设置成功, 不存在返回false
     * @description 设置过期时间
     */
    public Boolean expire(String key, long timeout, TimeUnit unit) {
        return stringRedisTemplate.expire(key, timeout, unit);
    }

    /**
     * @param key  需要设置过期时间的键
     * @param date 过期的时间
     * @return 是否设置成功
     * @description 设置过期时间, 指定过期时间
     */
    public Boolean expireAt(String key, Date date) {
        return stringRedisTemplate.expireAt(key, date);
    }

    /**
     * @param pattern
     * @return
     * @description 查找匹配的key
     */
    public Set<String> keys(String pattern) {
        return stringRedisTemplate.keys(pattern);
    }

    /**
     * @param key
     * @param dbIndex 目标数据库
     * @return
     * @description 将当前数据库的 key 移动到给定的数据库 db 当中
     */
    public Boolean move(String key, int dbIndex) {
        return stringRedisTemplate.move(key, dbIndex);
    }

    /**
     * @param key
     * @return 不存在则返回false
     * @description 移除 key 的过期时间，key 将持久保持
     */
    public Boolean persist(String key) {
        return stringRedisTemplate.persist(key);
    }

    /**
     * @param key
     * @param unit 时间单位
     * @return
     * @description 返回 key 的剩余的过期时间
     */
    public Long getExpire(String key, TimeUnit unit) {
        return stringRedisTemplate.getExpire(key, unit);
    }

    /**
     * @param key
     * @return
     * @description 返回 key 的剩余的过期时间
     */
    public Long getExpire(String key) {
        return stringRedisTemplate.getExpire(key);
    }

    /**
     * @return
     * @description 从当前数据库中随机返回一个 key
     */
    public String randomKey() {
        return stringRedisTemplate.randomKey();
    }

    /**
     * @param oldKey 旧名字
     * @param newKey 新名字
     * @description 修改 key 的名称 ,如果newKey已存在则会被覆盖,值也会变成oldKey的值 ,如果oldkey不存在则会报错 ;io.lettuce.core.RedisCommandExecutionException: ERR no such key
     */
    public void rename(String oldKey, String newKey) {
        stringRedisTemplate.rename(oldKey, newKey);
    }

    /**
     * @param oldKey
     * @param newKey
     * @return
     * @description 仅当 newkey 不存在时，将 oldKey 改名为 newkey,如果oldkey不存在则会报错;io.lettuce.core.RedisCommandExecutionException: ERR no such key
     */
    public Boolean renameIfAbsent(String oldKey, String newKey) {
        return stringRedisTemplate.renameIfAbsent(oldKey, newKey);
    }

    /**
     * @param key
     * @return
     * @description 返回 key 所储存的值的类型,普通的key不属于string类型,是none类型
     */
    public DataType type(String key) {
        return stringRedisTemplate.type(key);
    }
}
