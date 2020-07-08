package com.xiaoyuan.mq.rabbitmq.template.server.work;


import com.xiaoyuan.mq.rabbitmq.template.server.config.RabbitConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author liyuan
 * @description work工作队列
 * 一:轮询分发
 * 1、消费者1和消费者2获取到的消息内容是不同的，同一个消息只能被一个消费者获取。
 * 2、消费者1和消费者2获取到的消息的数量是相同的，一个是消费奇数号消息，一个是偶数。
 * 二:公平分发(能者多劳模式)
 * 1、根据消费的时间去分发,谁消费结束直接再次发送给他
 */
@RestController
public class WorkProvider {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init() {
//        rabbitTemplate.setConfirmCallback(RabbitConfig.confirmCallback);
    }

    @GetMapping("/work")
    public String send(int i) {
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());//24小时制
        String context = "hello " + date;
        //简单对列的情况下routingKey即为Q名
        new Random().ints().limit(i).forEach(a -> {
            // 通过实现 ConfirmCallback 接口，消息发送到 Broker 后触发回调，确认消息是否到达 Broker 服务器，也就是只确认是否正确到达 Exchange 中
            System.out.println("Sender : " + a + " " + context);
            // 可靠消息投递
            rabbitTemplate.setConfirmCallback(RabbitConfig.CONFIRM_CALLBACK);
            this.rabbitTemplate.convertAndSend(WorkParamConstant.QUEUE_NAME, a + " : " + context);
        });
        return "success";
    }
}
