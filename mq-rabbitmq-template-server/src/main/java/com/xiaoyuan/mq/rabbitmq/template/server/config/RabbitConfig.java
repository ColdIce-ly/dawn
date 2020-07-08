package com.xiaoyuan.mq.rabbitmq.template.server.config;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.Arrays;

/**
 * @author liyuan
 * @description todo
 * @date 2020/7/8 10:43
 */
public final class RabbitConfig {

    /**
     * 回调函数: 发送成功confirm确认
     */
    public static final RabbitTemplate.ConfirmCallback CONFIRM_CALLBACK = (correlationData, ack, cause) -> {
        if (ack) {
            //如果confirm返回成功 则进行更新
            System.out.println("推送成功.....");
        } else {
            //失败则进行具体的后续操作:重试 或者补偿等手段
            System.err.println("异常处理...");
        }
    };


    /**
     * 回调函数: 消息被发送至交换机,路由不到队列
     */
    public static final RabbitTemplate.ReturnCallback RETURN_CALLBACK = (Message message, int replyCode, String replyText, String exchange, String routingKey) -> {
        System.out.println("correlationId:{}" + message.getMessageProperties().getCorrelationId());
        System.out.println("replyText:{}" + replyText);
        System.out.println("消息内容:{}" + Arrays.toString(message.getBody()));
        System.out.println("replyCode:{}" + replyCode);
        System.out.println("交换机:{}" + exchange);
        System.out.println("routingKey:{}" + routingKey);
        System.out.println("需要更新数据库日志表得消息记录为不可达");
    };

}
