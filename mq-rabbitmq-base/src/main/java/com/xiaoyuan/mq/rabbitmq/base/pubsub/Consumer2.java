package com.xiaoyuan.mq.rabbitmq.base.pubsub;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;
import com.xiaoyuan.mq.rabbitmq.base.util.RabbitMqConnectionUtil;

import java.nio.charset.StandardCharsets;

public class Consumer2 {

    public static void main(String[] argv) throws Exception {

        // 获取到连接以及mq通道
        Connection connection = RabbitMqConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        // 声明队列
        channel.queueDeclare(ParamConstant.QUEUE_NAME_TWO, false, false, false, null);

        // 绑定队列到交换机
        channel.queueBind(ParamConstant.QUEUE_NAME_TWO, ParamConstant.EXCHANGE_NAME, "pubsub");

        // 同一时刻服务器只会发一条消息给消费者
        channel.basicQos(1);

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            //开启这行 表示使用手动确认模式
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println(" [x] Received '" + message + "'");
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        };
        channel.basicConsume(ParamConstant.QUEUE_NAME_TWO, false, deliverCallback, consumerTag -> { });

    }
}
