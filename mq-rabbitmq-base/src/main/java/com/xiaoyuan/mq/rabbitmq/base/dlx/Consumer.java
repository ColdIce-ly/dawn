package com.xiaoyuan.mq.rabbitmq.base.dlx;

import com.rabbitmq.client.Channel;
import com.xiaoyuan.mq.rabbitmq.base.config.RabbitMQConnection;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @description 简单队列的使用, 只需要channel管道, 和queue队列
 */
public class Consumer {

    public static void main(String[] argv) throws Exception {

        // 从连接中创建通道
        Channel channel = RabbitMQConnection.createChannel();

        //声明正常的队列
        String exchangeType = "direct";

        Map<String,Object> queueArgs = new HashMap<>();
        // 正常队列上绑定死信交换机
        queueArgs.put("x-dead-letter-exchange",ParamConstant.DELAY_EXCHANGE_NAME);
        // 设置队列中的消息10s没有被消费就会过期
        queueArgs.put("x-message-ttl", 10000);
        // 队列的长度
        queueArgs.put("x-max-length", 10);
        channel.queueDeclare(ParamConstant.QUEUE_NAME,true,false,false,queueArgs);
        channel.queueBind(ParamConstant.QUEUE_NAME,ParamConstant.EXCHANGE_NAME,ParamConstant.ROUTING_KEY);

        // 声明死信队列
        channel.exchangeDeclare(ParamConstant.DELAY_EXCHANGE_NAME,exchangeType,true,false,null);
        channel.queueDeclare(ParamConstant.DELAY_QUEUE_NAME,true,false,false,null);
        channel.queueBind(ParamConstant.DELAY_QUEUE_NAME,ParamConstant.DELAY_EXCHANGE_NAME,ParamConstant.DELAY_ROUTING_KEY);

        channel.basicConsume(ParamConstant.DELAY_QUEUE_NAME, false, (consumerTag, delivery) -> {
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println(" [Consumer] Received '" + message + "'");
        }, consumerTag -> {
        });

    }
}
