package com.xiaoyuan.mq.rabbitmq.base.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;
import com.xiaoyuan.mq.rabbitmq.base.util.RabbitMqConnectionUtil;

import java.nio.charset.StandardCharsets;

/**
 * @description 简单队列的使用,只需要channel管道,和queue队列
 */
public class Consumer {

    public static void main(String[] argv) throws Exception {

        // 获取到连接以及mq通道
        Connection connection = RabbitMqConnectionUtil.getConnection();
        // 从连接中创建通道
        Channel channel = connection.createChannel();
        // 声明队列
        channel.queueDeclare(ParamConstant.QUEUE_NAME, false, false, false, null);

        // 定义队列的消费者

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println(" [x] Received '" + message + "'");
        };
        channel.basicConsume(ParamConstant.QUEUE_NAME, true, deliverCallback, consumerTag -> { });

    }
}
