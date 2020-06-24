package com.xiaoyuan.mq.rabbitmq.base.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.xiaoyuan.mq.rabbitmq.base.util.RabbitMqConnectionUtil;

/**
 * @author liyuan
 * @description 主题模式
 * # :匹配多个单词
 * * :匹配一个单词
 * 满足条件就会接受消息,不满足都不会接受,消息丢失,消息会消费多次
 */
public class Provider {

    public static void main(String[] argv) throws Exception {
        // 获取到连接以及mq通道
        Connection connection = RabbitMqConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        // 声明exchange
        channel.exchangeDeclare(ParamConstant.EXCHANGE_NAME, "topic");

        // 消息内容
        String message = "Hello World!!";
        // 只有消费者2接受
//        String key = "asdas.0.1";
        // 只有消费者1接受
//        String key = "routekey.122.sds";
        // 两个都可以接受
        String key = "routekey.1";
        channel.basicPublish(ParamConstant.EXCHANGE_NAME, key, null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");

        channel.close();
        connection.close();
    }
}
