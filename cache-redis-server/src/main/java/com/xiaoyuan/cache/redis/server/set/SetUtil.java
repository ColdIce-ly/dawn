package com.xiaoyuan.cache.redis.server.set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Collection;
import java.util.List;

/**
 * @author liyuan
 * @create 2019-12-30-10:46
 */
public class SetUtil {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private String key = "存入的key键";
    private String value = "需要存入的值value";
    private long expireTime = 60 * 60 * 24 * 30;

    /**
     * sadd key member [member ...]
     * 将一个或多个 member 元素加入到集合 key 当中，已经存在于集合的 member 元素将
     * 被忽略。
     */
    public Long sAdd(String key, String... values) {
        return stringRedisTemplate.opsForSet().add(key, values);
    }

    /**
     * srem key member [member ...]
     * 移除集合 key 中的一个或多个 member 元素，不存在的 member 元素会被忽略。
     */
    public Long sRemove(String key, Object... values) {
        return stringRedisTemplate.opsForSet().remove(key, values);
    }

    /**
     * 获取集合所有元素
     *
     * @param key
     * @return
     */
    public java.util.Set<String> sMembers(String key) {
        return stringRedisTemplate.opsForSet().members(key);
    }

    /**
     * 判断集合是否包含value
     * sismember key member
     * 判断 member 元素是否集合 key 的成员。
     */
    public Boolean sIsMember(String key, Object value) {
        return stringRedisTemplate.opsForSet().isMember(key, value);
    }

    /**
     * scard key
     * 返回集合 key 的基数(集合中元素的数量)。
     */
    public Long sSize(String key) {
        return stringRedisTemplate.opsForSet().size(key);
    }

    /**
     * 将元素value从一个集合移到另一个集合
     * <p>
     * smove source destination member 将 member 元素从 source 集合移动到 destination 集合。
     * SMOVE 是原子性操作。
     * 如果 source 集合不存在或不包含指定的 member 元素，则 SMOVE 命令不执行任何操
     * 作，仅返回 0 。否则， member 元素从 source 集合中被移除，并添加到 destination 集
     * 合中去。
     * 当 destination 集合已经包含 member 元素时，SMOVE 命令只是简单地将 source 集
     * 合中的 member 元素删除。
     *
     * @param key
     * @param value
     * @param destKey
     * @return
     */
    public Boolean sMove(String key, String value, String destKey) {
        return stringRedisTemplate.opsForSet().move(key, value, destKey);
    }

    /**
     * 移除并返回集合的一个随机元素
     *
     * @param key
     * @param count 不传默认为1
     * @return
     */
    public List<String> sPop(String key, Integer count) {
        return stringRedisTemplate.opsForSet().pop(key, count);
    }

    /**
     * 随机获取集合中的一个元素
     *
     * @param key
     * @return
     */
    public String sRandomMember(String key) {
        return stringRedisTemplate.opsForSet().randomMember(key);
    }

    /**
     * 随机获取集合中count个元素
     *
     * @param key
     * @param count
     * @return
     */
    public List<String> sRandomMembers(String key, long count) {
        return stringRedisTemplate.opsForSet().randomMembers(key, count);
    }

    /**
     * 获取两个集合的交集
     * sinter key [key ...]
     * 返回一个集合的全部成员，该集合是所有给定集合的交集。
     * 不存在的 key 被视为空集。
     * 当给定集合当中有一个空集时，结果也为空集(根据集合运算定律)。
     *
     * @param key
     * @param otherKey
     * @return
     */
    public java.util.Set<String> sIntersect(String key, String otherKey) {
        return stringRedisTemplate.opsForSet().intersect(key, otherKey);
    }

    /**
     * 获取key集合与多个集合的交集
     *
     * @param key
     * @param otherKeys
     * @return
     */
    public java.util.Set<String> sIntersect(String key, Collection<String> otherKeys) {
        return stringRedisTemplate.opsForSet().intersect(key, otherKeys);
    }


    /**
     * key集合与otherKey集合的交集存储到destKey集合中
     * <p>
     * sinterstore destination key [key ...]
     * 这个命令类似于 SINTER 命令，但它将结果保存到 destination 集合，而不是简单地
     * 返回结果集。
     * 如果 destination 集合已经存在，则将其覆盖。
     * destination 可以是 key 本身。
     *
     * @param key
     * @param otherKey
     * @param destKey
     * @return
     */
    public Long sIntersectAndStore(String key, String otherKey, String destKey) {
        return stringRedisTemplate.opsForSet().intersectAndStore(key, otherKey,
                destKey);
    }

    /**
     * key集合与多个集合的交集存储到destKey集合中
     *
     * @param key
     * @param otherKeys
     * @param destKey
     * @return
     */
    public Long sIntersectAndStore(String key, Collection<String> otherKeys,
                                   String destKey) {
        return stringRedisTemplate.opsForSet().intersectAndStore(key, otherKeys,
                destKey);
    }


    /**
     * 获取两个集合的差集
     * sdiff key [key ...]
     * 返回一个集合的全部成员，该集合是所有给定集合之间的差集。
     * 不存在的 key 被视为空集。
     *
     * @param key
     * @param otherKey
     * @return
     */
    public java.util.Set<String> sDifference(String key, String otherKey) {
        return stringRedisTemplate.opsForSet().difference(key, otherKey);
    }

    /**
     * 获取key集合与多个集合的差集
     *
     * @param key
     * @param otherKeys
     * @return
     */
    public java.util.Set<String> sDifference(String key, Collection<String> otherKeys) {
        return stringRedisTemplate.opsForSet().difference(key, otherKeys);
    }

    /**
     * key集合与otherKey集合的差集存储到destKey中
     *
     * sdiffstore destination key [key ...]
     * 这个命令的作用和 SDIFF 类似，但它将结果保存到 destination 集合，而不是简单地
     * 返回结果集。
     * 如果 destination 集合已经存在，则将其覆盖。
     * destination 可以是 key 本身
     *
     * @param key
     * @param otherKey
     * @param destKey
     * @return
     */
    public Long sDifference(String key, String otherKey, String destKey) {
        return stringRedisTemplate.opsForSet().differenceAndStore(key, otherKey,
                destKey);
    }

    /**
     * key集合与多个集合的差集存储到destKey中
     *
     * @param key
     * @param otherKeys
     * @param destKey
     * @return
     */
    public Long sDifference(String key, Collection<String> otherKeys,
                            String destKey) {
        return stringRedisTemplate.opsForSet().differenceAndStore(key, otherKeys,
                destKey);
    }

    /**
     * 获取两个集合的并集
     * sunion key [key ...]
     * 返回一个集合的全部成员，该集合是所有给定集合的并集。
     * 不存在的 key 被视为空集。
     *
     * @param key
     * @param otherKeys
     * @return
     */
    public java.util.Set<String> sUnion(String key, String otherKeys) {
        return stringRedisTemplate.opsForSet().union(key, otherKeys);
    }

    /**
     * 获取key集合与多个集合的并集
     *
     * @param key
     * @param otherKeys
     * @return
     */
    public java.util.Set<String> sUnion(String key, Collection<String> otherKeys) {
        return stringRedisTemplate.opsForSet().union(key, otherKeys);
    }

    /**
     * key集合与otherKey集合的并集存储到destKey中
     * <p>
     * sunionstore destination key [key ...]
     * 这个命令类似于 SUNION 命令，但它将结果保存到 destination 集合，而不是简单地
     * 返回结果集。
     * 如果 destination 已经存在，则将其覆盖。
     * destination 可以是 key 本身。
     *
     * @param key
     * @param otherKey
     * @param destKey
     * @return
     */
    public Long sUnionAndStore(String key, String otherKey, String destKey) {
        return stringRedisTemplate.opsForSet().unionAndStore(key, otherKey, destKey);
    }

    /**
     * key集合与多个集合的并集存储到destKey中
     *
     * @param key
     * @param otherKeys
     * @param destKey
     * @return
     */
    public Long sUnionAndStore(String key, Collection<String> otherKeys,
                               String destKey) {
        return stringRedisTemplate.opsForSet().unionAndStore(key, otherKeys, destKey);
    }


    /**
     * 随机获取集合中count个元素并且去除重复的
     *
     * @param key
     * @param count
     * @return
     */
    public java.util.Set<String> sDistinctRandomMembers(String key, long count) {
        return stringRedisTemplate.opsForSet().distinctRandomMembers(key, count);
    }

    /**
     *
     * @param key
     * @param options
     * @return
     */
    public Cursor<String> sScan(String key, ScanOptions options) {
        return stringRedisTemplate.opsForSet().scan(key, options);
    }
}
