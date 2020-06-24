package com.xiaoyuan.mq.rabbitmq.base.work;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;
import com.xiaoyuan.mq.rabbitmq.base.util.RabbitMqConnectionUtil;

import java.nio.charset.StandardCharsets;

/**
 * @author liyuan
 * @description work工作队列
 * 一:轮询分发
 * 1、消费者1和消费者2获取到的消息内容是不同的，同一个消息只能被一个消费者获取。
 * 2、消费者1和消费者2获取到的消息的数量是相同的，一个是消费奇数号消息，一个是偶数。
 * 二:公平分发(能者多劳模式)
 * 1、根据消费的时间去分发,谁消费结束直接再次发送给他
 */
public class Consumer1 {

    public static void main(String[] argv) throws Exception {

        // 获取到连接以及mq通道
        Connection connection = RabbitMqConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        // 声明队列
        channel.queueDeclare(ParamConstant.QUEUE_NAME, false, false, false, null);

        // 同一时刻服务器只会发一条消息给消费者
        channel.basicQos(1);

        // 定义队列的消费者

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            //开启这行 表示使用手动确认模式
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println(" [x] Received '" + message + "'");
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        };
        channel.basicConsume(ParamConstant.QUEUE_NAME, false, deliverCallback, consumerTag -> {
        });

    }
}
