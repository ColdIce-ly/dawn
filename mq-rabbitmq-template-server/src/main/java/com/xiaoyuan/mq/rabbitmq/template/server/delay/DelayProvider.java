package com.xiaoyuan.mq.rabbitmq.template.server.delay;

import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author liyuan
 * @description 延时队列
 */
@RestController
public class DelayProvider {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    //回调函数: confirm确认
    final RabbitTemplate.ConfirmCallback confirmCallback = (correlationData, ack, cause) -> {
        if (ack) {
            //如果confirm返回成功 则进行更新
            System.out.println("推送成功.....");
        } else {
            //失败则进行具体的后续操作:重试 或者补偿等手段
            System.err.println("异常处理...");
        }
    };

    @GetMapping("/delay")
    public String send(int i) {
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());//24小时制
        String message = "hello " + date;

        String routingKey;
        // 两个都可以消费
        routingKey = "delete";
        // 通过实现 ConfirmCallback 接口，消息发送到 Broker 后触发回调，确认消息是否到达 Broker 服务器，也就是只确认是否正确到达 Exchange 中
        rabbitTemplate.setConfirmCallback(confirmCallback);
        System.out.println("Sender : " + message);
        this.rabbitTemplate.convertAndSend(DelayParamConstant.EXCHANGE_NAME, routingKey, message, message1 -> {
            message1.getMessageProperties().setHeader(MessageProperties.X_DELAY, 30000);//设置延迟时间
            return message1;
        });
        return "success";
    }

}
