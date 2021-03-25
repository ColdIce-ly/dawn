package com.xiaoyuan.mq.rabbitmq.base.direct;


import com.rabbitmq.client.Channel;
import com.xiaoyuan.mq.rabbitmq.base.config.RabbitMQConnection;

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
        Channel channel = RabbitMQConnection.createChannel();
        channel.exchangeDeclare(ParamConstant.EXCHANGE_NAME, ParamConstant.EXCHANGE_TYPE);

        // 消息内容
        String message = "商品信息变更!";


        // 两个都接收到
        // String key = "delete";

        // 只有1接收到
        // String key = "add";


        // 只有2接收到
        // String key = "update";


        // 两个都接收不到 ,此时消息丢失
         String key = "query";


        channel.basicPublish(ParamConstant.EXCHANGE_NAME, key, null, message.getBytes());
        System.out.println(" [事件通知] Send '" + message + "'");
    }
}
