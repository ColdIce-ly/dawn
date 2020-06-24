package com.xiaoyuan.mq.rabbitmq.base.pubsub;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.xiaoyuan.mq.rabbitmq.base.util.RabbitMqConnectionUtil;

public class Provider {

    /**
     * 消息发送到没有队列绑定的交换机时，消息将丢失，因为，交换机没有存储消息的能力，消息只能存在在队列中。
     * @param argv
     * @throws Exception
     */
    public static void main(String[] argv) throws Exception {
        // 获取到连接以及mq通道
        Connection connection = RabbitMqConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        // 声明exchange
        channel.exchangeDeclare(ParamConstant.EXCHANGE_NAME, "fanout");

        // 消息内容
        String message = "Hello World!";
        channel.basicPublish(ParamConstant.EXCHANGE_NAME, "pubsub", null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");

        channel.close();
        connection.close();
    }
}
