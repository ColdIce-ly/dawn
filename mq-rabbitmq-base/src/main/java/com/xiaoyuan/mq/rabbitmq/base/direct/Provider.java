package com.xiaoyuan.mq.rabbitmq.base.direct;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.xiaoyuan.mq.rabbitmq.base.util.RabbitMqConnectionUtil;

/**
 * @author liyuan
 * @description 直接模式
 * 可以具备多个接受规则(路由?)
 * 满足条件就会接受消息,不满足都不会接受,消息丢失,消息会消费多次
 */
public class Provider {

    /**
     * @param argv
     * @throws Exception
     */
    public static void main(String[] argv) throws Exception {
        // 获取到连接以及mq通道
        Connection connection = RabbitMqConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        // 声明exchange
        channel.exchangeDeclare(ParamConstant.EXCHANGE_NAME, "direct");

        // 消息内容
        String message = "我下架了商品!";
        // 两个都可以消费
//        String key = "delete";
        // 1可以消费
//        String key = "add";
        // 2可以消费
//        String key = "update";
        // 两个都不可以消费
        String key = "query";
        channel.basicPublish(ParamConstant.EXCHANGE_NAME, key, null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");

        channel.close();
        connection.close();
    }
}
