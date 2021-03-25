package com.xiaoyuan.mq.rabbitmq.base.pubsub;


import com.rabbitmq.client.Channel;
import com.xiaoyuan.mq.rabbitmq.base.config.RabbitMQConnection;

public class Provider {

    public static void main(String[] argv) throws Exception {
        Channel channel = RabbitMQConnection.createChannel();

        // 声明exchange
        channel.exchangeDeclare(ParamConstant.EXCHANGE_NAME, ParamConstant.EXCHANGE_TYPE);

        // 消息内容
        String message = "你关注的主播开播了!直播地址http://xxxxxxxx";
        channel.basicPublish(ParamConstant.EXCHANGE_NAME, ParamConstant.ROUTING_KEY, null, message.getBytes());
        System.out.println(" [Provider] Send '" + message + "'");

    }
}
