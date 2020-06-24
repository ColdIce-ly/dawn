package com.xiaoyuan.mq.rabbitmq.base.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @description 连接工具
 */
public class RabbitMqConnectionUtil {

    public static Connection getConnection() throws Exception {
        //定义连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置服务地址
        factory.setHost("11.11.11.121");
        //端口
        factory.setPort(5672);
        //设置账号信息，用户名、密码、vhost
        factory.setUsername("guest");
        factory.setPassword("guest");
        // 通过工程获取连接
        return factory.newConnection();
    }
}