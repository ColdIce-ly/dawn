package com.xiaoyuan.mq.rabbitmq.base.topic;


import com.rabbitmq.client.Channel;
import com.xiaoyuan.mq.rabbitmq.base.config.RabbitMQConnection;

import java.nio.charset.StandardCharsets;

/**
 * @author liyuan
 * @description 主题模式
 * # :匹配多个单词
 * * :匹配一个单词
 * 满足条件就会接受消息,不满足都不会接受,消息丢失,消息会消费多次
 */
public class Consumer1 {

    public static void main(String[] argv) throws Exception {

        Channel channel = RabbitMQConnection.createChannel();

        // 声明队列
        channel.queueDeclare(ParamConstant.QUEUE_NAME_ONE, false, false, false, null);

        // 绑定队列到交换机
        channel.queueBind(ParamConstant.QUEUE_NAME_ONE, ParamConstant.EXCHANGE_NAME, "routekey.#");

        // 同一时刻服务器只会发一条消息给消费者
        channel.basicQos(1);

        channel.basicConsume(ParamConstant.QUEUE_NAME_ONE, false, (consumerTag, delivery) -> {
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println(" [Consumer1] Received '" + message + "'");
        }, consumerTag -> {
        });
    }
}
