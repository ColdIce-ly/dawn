package com.xiaoyuan.mq.rabbitmq.template.server.direct;

import com.xiaoyuan.mq.rabbitmq.template.server.config.RabbitConfig;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author liyuan
 * @description 直接模式
 * 可以具备多个接受规则(路由?)
 * 满足条件就会接受消息,不满足都不会接受,消息丢失,消息会消费多次
 */
@RestController
public class DirectProvider {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init() {
        // 默认回调
        rabbitTemplate.setConfirmCallback(RabbitConfig.CONFIRM_CALLBACK);
        rabbitTemplate.setReturnCallback(RabbitConfig.RETURN_CALLBACK);
    }

    @GetMapping("/direct")
    public String send(int i) {
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());//24小时制
        String context = "hello " + date;
        // 消息内容
        String message = "我下架了商品!";
        // 构建correlationData 用于做可靠性投递得,ID:必须为全局唯一的 根据业务规则
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        String routingKey;
        // 两个都可以消费
        routingKey = "delete";
        // 1可以消费
//        routingKey = "add";
        // 2可以消费
//        routingKey = "update";
        // 两个都不可以消费
//        routingKey = "query";
        // 通过实现 ConfirmCallback 接口，消息发送到 Broker 后触发回调，确认消息是否到达 Broker 服务器，也就是只确认是否正确到达 Exchange 中
        System.out.println("Sender : " + message);
        this.rabbitTemplate.convertAndSend(DirectParamConstant.EXCHANGE_NAME, routingKey, message, correlationData);

        return "success";
    }

}
