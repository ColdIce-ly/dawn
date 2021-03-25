package com.xiaoyuan.mq.rabbitmq.base.topic;

import com.rabbitmq.client.Channel;
import com.xiaoyuan.mq.rabbitmq.base.config.RabbitMQConnection;

/**
 * @author liyuan
 * @description 主题模式
 * # :匹配多个单词
 * * :匹配一个单词
 * 满足条件就会接受消息,不满足都不会接受,消息丢失,消息会消费多次
 */
public class Provider {

    public static void main(String[] argv) throws Exception {
        Channel channel = RabbitMQConnection.createChannel();
        // 声明exchange
        channel.exchangeDeclare(ParamConstant.EXCHANGE_NAME, ParamConstant.EXCHANGE_TYPE);

        // 消息内容
        String message = "Hello World!!";


        // 都不可以接受
        String key = "abcdef";


        // 只有消费者2接受
        // String key = "asdas.0.1";


        // 只有消费者1接受
        // String key = "routekey.122.sds";


        // 两个都可以接受
        // String key = "routekey.1";

        channel.basicPublish(ParamConstant.EXCHANGE_NAME, key, null, message.getBytes());
        System.out.println(" [Provider] Sent '" + message + "'");

    }
}
