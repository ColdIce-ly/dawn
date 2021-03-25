package com.xiaoyuan.mq.rabbitmq.base.work;


import com.rabbitmq.client.Channel;
import com.xiaoyuan.mq.rabbitmq.base.config.RabbitMQConnection;

/**
 * @author liyuan
 * @description work工作队列
 * 一:轮询分发
 * 1、消费者1和消费者2获取到的消息内容是不同的，同一个消息只能被一个消费者获取。
 * 2、消费者1和消费者2获取到的消息的数量是相同的，一个是消费奇数号消息，一个是偶数。
 * 二:公平分发(能者多劳模式)
 * 1、根据消费的时间去分发,谁消费结束直接再次发送给他
 */
public class Provider {

    public static void main(String[] argv) throws Exception {
        // 从连接中创建通道
        Channel channel = RabbitMQConnection.createChannel();
        // 声明队列
        channel.queueDeclare(ParamConstant.QUEUE_NAME, false, false, false, null);

        for (int i = 0; i < 1000; i++) {
            // 推送100条消息
            String message = "" + i;
            System.out.println(" [Provider] Sent '" + message + "'");
            channel.basicPublish("", ParamConstant.QUEUE_NAME, null, message.getBytes());
        }
        channel.close();
    }
}
