package com.xiaoyuan.mq.rabbitmq.base.simple;

import com.rabbitmq.client.Channel;
import com.xiaoyuan.mq.rabbitmq.base.config.RabbitMQConnection;

/**
 * @author Administrator
 * @description 简单队列的使用, 只需要channel管道, 和queue队列
 */
public class Provider {

    public static void main(String[] argv) throws Exception {
        Channel channel = RabbitMQConnection.createChannel();
        channel.queueDeclare(ParamConstant.QUEUE_NAME, false, false, false, null);
        // 消息内容
        String message = "Hello World!";
        // 推送消息
        channel.basicPublish("", ParamConstant.QUEUE_NAME, null, message.getBytes());
        System.out.println(" [Provider] Send '" + message + "'");
        channel.close();
    }
}