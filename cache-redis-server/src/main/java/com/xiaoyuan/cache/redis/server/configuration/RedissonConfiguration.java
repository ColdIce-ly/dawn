package com.xiaoyuan.cache.redis.server.configuration;

import io.netty.channel.nio.NioEventLoopGroup;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.Codec;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ClassUtils;

/**
 * @author liyuan
 * @date  2019-12-29-23:17
 */
@Configuration
public class RedissonConfiguration {

    private String address = "redis://r-bp1u1gb3eqd388g38jpd.redis.rds.aliyuncs.com:6379";
    private int connectionMinimumIdleSize = 10;
    private int idleConnectionTimeout = 10000;
    private int connectTimeout = 10000;
    private int timeout = 3000;
    private int retryAttempts = 3;
    private int retryInterval = 1500;
    private String password = "Hellosyman1";
    private int subscriptionsPerConnection = 5;
    private String clientName = null;
    private int subscriptionConnectionMinimumIdleSize = 1;
    private int subscriptionConnectionPoolSize = 50;
    private int connectionPoolSize = 64;
    private int database = 77;
    private int dnsMonitoringInterval = 5000;

    @Bean(destroyMethod = "shutdown")
    RedissonClient redisson() throws Exception {
        Config config = new Config();
        config.useSingleServer().setAddress(address)
                .setConnectionMinimumIdleSize(connectionMinimumIdleSize)
                .setConnectionPoolSize(connectionPoolSize)
                .setDatabase(database)
                .setDnsMonitoringInterval(dnsMonitoringInterval)
                .setSubscriptionConnectionMinimumIdleSize(subscriptionConnectionMinimumIdleSize)
                .setSubscriptionConnectionPoolSize(subscriptionConnectionPoolSize)
                .setSubscriptionsPerConnection(subscriptionsPerConnection)
                .setClientName(clientName)
                .setRetryAttempts(retryAttempts)
                .setRetryInterval(retryInterval)
                .setTimeout(timeout)
                .setConnectTimeout(connectTimeout)
                .setIdleConnectionTimeout(idleConnectionTimeout)
                .setPassword(password);

        // cluster集群化配置
//        config.useClusterServers()
//                .addNodeAddress("redis://11.11.11.111:6379")
//                .addNodeAddress("redis://11.11.11.112:6379")
//                .addNodeAddress("redis://11.11.11.113:6379")
//                .addNodeAddress("redis://11.11.11.114:6379")
//                .addNodeAddress("redis://11.11.11.115:6379")
//                .addNodeAddress("redis://11.11.11.116:6379")
//                .addNodeAddress("redis://11.11.11.117:6379")
//                .addNodeAddress("redis://11.11.11.118:6379")
//                .addNodeAddress("redis://11.11.11.119:6379")
//                .setMasterConnectionMinimumIdleSize(connectionMinimumIdleSize)
//                .setMasterConnectionPoolSize(connectionPoolSize)
//                .setDnsMonitoringInterval(dnsMonitoringInterval)
//                .setSubscriptionConnectionMinimumIdleSize(subscriptionConnectionMinimumIdleSize)
//                .setSubscriptionConnectionPoolSize(subscriptionConnectionPoolSize)
//                .setClientName(clientName)
//                .setRetryAttempts(retryAttempts)
//                .setRetryInterval(retryInterval)
//                .setTimeout(timeout)
//                .setConnectTimeout(connectTimeout)
//                .setIdleConnectionTimeout(idleConnectionTimeout)
//                .setPassword(password);

        Codec codec = (Codec) ClassUtils.forName("org.redisson.codec.JsonJacksonCodec", ClassUtils.getDefaultClassLoader()).newInstance();
        config.setCodec(codec);
        config.setEventLoopGroup(new NioEventLoopGroup());

        return Redisson.create(config);
    }

}
