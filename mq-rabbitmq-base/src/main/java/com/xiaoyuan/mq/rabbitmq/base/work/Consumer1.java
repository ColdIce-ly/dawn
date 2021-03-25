package com.xiaoyuan.mq.rabbitmq.base.work;


import com.rabbitmq.client.Channel;
import com.xiaoyuan.mq.rabbitmq.base.config.RabbitMQConnection;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

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

        Channel channel = RabbitMQConnection.createChannel();
        channel.queueDeclare(ParamConstant.QUEUE_NAME, false, false, false, null);

        // 同一时刻服务器只会发一条消息给消费者
        channel.basicQos(1);

        AtomicInteger atomicInteger = new AtomicInteger(0);

        channel.basicConsume(ParamConstant.QUEUE_NAME, false, (consumerTag, delivery) -> {

            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println(" [Consumer1] Received '" + message + "'");
            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            System.out.println("Consumer1 消费了:" + atomicInteger.incrementAndGet() + "次");
        }, consumerTag -> {
        });
    }
}
