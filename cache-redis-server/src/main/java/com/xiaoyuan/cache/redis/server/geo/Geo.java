package com.xiaoyuan.cache.redis.server.geo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Metric;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.GeoOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author liyuan
 * @create 2020-01-02-0:10
 */
@RestController
@RequestMapping("/geo")
public class Geo {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    // http://www.imooc.com/article/280622?block_id=tuijian_wz

    /**
     * <h2>获取两个城市之间的距离</h2>
     * @param city1 第一个城市
     * @param city2 第二个城市
     * @param metric {@link Metric} 单位信息, 可以是 null
     * @return {@link Distance}
     */
    public Distance getTwoCityDistance(String key, String city1, String city2, Metric metric){
        GeoOperations<String, String> geoOperations = stringRedisTemplate.opsForGeo();
        return metric == null ? geoOperations.distance(key, city1, city2) : geoOperations.distance(key, city1, city2, metric);
    }

    /**
     * <h2>根据给定地理位置坐标获取指定范围内的地理位置集合</h2>
     * @param within {@link Circle} 中心点和距离
     * @param args {@link RedisGeoCommands.GeoRadiusCommandArgs} 限制返回的个数和排序方式, 可以是 null
     * @return {@link RedisGeoCommands.GeoLocation}
     * */
    public GeoResults<RedisGeoCommands.GeoLocation<String>> getPointRadius(String key, Circle within, RedisGeoCommands.GeoRadiusCommandArgs args){
        GeoOperations<String, String> geoOperations = stringRedisTemplate.opsForGeo();
        return args == null ?
                geoOperations.radius(key, within) : geoOperations.radius(key, within, args);
    }

    /**
     * <h2>根据给定地理位置获取指定范围内的地理位置集合</h2>
     * */
    public GeoResults<RedisGeoCommands.GeoLocation<String>> getMemberRadius(String key,
            String member, Distance distance, RedisGeoCommands.GeoRadiusCommandArgs args){
        GeoOperations<String, String> geoOperations = stringRedisTemplate.opsForGeo();
        return args == null ?
                geoOperations.radius(key, member, distance) : geoOperations.radius(key, member, distance, args);
    }

    /**
     * <h2>获取某个地理位置的 geohash 值</h2>
     * @param cities 给定城市 key
     * @return city geohashs
     */
    public List<String> getCityGeoHash(String key, String[] cities) {

        GeoOperations<String, String> ops = stringRedisTemplate.opsForGeo();

        return ops.hash(key, cities);
    }
}
