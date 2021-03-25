package com.xiaoyuan.mq.rabbitmq.base.simple;

import com.rabbitmq.client.Channel;
import com.xiaoyuan.mq.rabbitmq.base.config.RabbitMQConnection;

import java.nio.charset.StandardCharsets;

/**
 * @description 简单队列的使用, 只需要channel管道, 和queue队列
 */
public class Consumer {

    public static void main(String[] argv) throws Exception {

        // 从连接中创建通道
        Channel channel = RabbitMQConnection.createChannel();
        // 声明队列
        channel.queueDeclare(ParamConstant.QUEUE_NAME, false, false, false, null);

        // 定义队列的消费者

        channel.basicConsume(ParamConstant.QUEUE_NAME, true, (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println(" [Consumer] Received '" + message + "'");
        }, consumerTag -> {
        });

    }
}
