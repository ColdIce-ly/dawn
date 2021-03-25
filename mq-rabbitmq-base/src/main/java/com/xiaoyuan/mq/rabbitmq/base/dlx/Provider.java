package com.xiaoyuan.mq.rabbitmq.base.dlx;

import com.rabbitmq.client.Channel;
import com.xiaoyuan.mq.rabbitmq.base.config.RabbitMQConnection;

/**
 * @author Administrator
 * @description 简单队列的使用, 只需要channel管道, 和queue队列
 */
public class Provider {

    public static void main(String[] argv) throws Exception {
        Channel channel = RabbitMQConnection.createChannel();

        // 交换机
        channel.exchangeDeclare(ParamConstant.EXCHANGE_NAME, "direct", true, false, null);

        // 消息内容
        String message = "下单啦!";

        // 队列
//        Map<String, Object> queueArgs = new HashMap<>();
//        // 设置队列中的消息10s没有被消费就会过期
//        queueArgs.put("x-message-ttl", 10000);
//        // 队列的长度
//        queueArgs.put("x-max-length", 10);
//        channel.queueDeclare(ParamConstant.QUEUE_NAME, true, false, false, queueArgs);

//        //绑定
//        channel.queueBind(ParamConstant.QUEUE_NAME, ParamConstant.EXCHANGE_NAME, ParamConstant.ROUTING_KEY);

        for (int i = 0; i < 10; i++) {
            channel.basicPublish(ParamConstant.EXCHANGE_NAME, ParamConstant.ROUTING_KEY, null, (i + message).getBytes());
        }
    }
}