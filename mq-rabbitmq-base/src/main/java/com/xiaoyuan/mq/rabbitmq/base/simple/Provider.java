package com.xiaoyuan.mq.rabbitmq.base.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.xiaoyuan.mq.rabbitmq.base.util.RabbitMqConnectionUtil;

/**
 * @author Administrator
 * @description 简单队列的使用, 只需要channel管道, 和queue队列
 */
public class Provider {

    public static void main(String[] argv) throws Exception {
        // 获取到连接以及mq通道
        Connection connection = RabbitMqConnectionUtil.getConnection();
        // 从连接中创建通道
        Channel channel = connection.createChannel();
        // 声明（创建）队列
        channel.queueDeclare(ParamConstant.QUEUE_NAME, false, false, false, null);
        // 消息内容
        String message = "Hello World!";
        // 推送消息
        channel.basicPublish("", ParamConstant.QUEUE_NAME, null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");
        //关闭通道和连接
        channel.close();
        connection.close();
    }
}