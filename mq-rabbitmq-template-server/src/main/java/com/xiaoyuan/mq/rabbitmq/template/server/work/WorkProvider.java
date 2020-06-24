package com.xiaoyuan.mq.rabbitmq.template.server.work;


import com.xiaoyuan.mq.rabbitmq.template.server.simple.ParamConstant;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

    //回调函数: confirm确认
    final RabbitTemplate.ConfirmCallback confirmCallback = (correlationData, ack, cause) -> {
        if(ack){
            //如果confirm返回成功 则进行更新
            System.out.println("推送成功.....");
        } else {
            //失败则进行具体的后续操作:重试 或者补偿等手段
            System.err.println("异常处理...");
        }
    };

    @GetMapping("/work")
    public String send(int i) {
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());//24小时制
        String context = "hello " + date;
        //简单对列的情况下routingKey即为Q名
        new Random().ints().limit(i).forEach(a -> {
            // 通过实现 ConfirmCallback 接口，消息发送到 Broker 后触发回调，确认消息是否到达 Broker 服务器，也就是只确认是否正确到达 Exchange 中
            rabbitTemplate.setConfirmCallback(confirmCallback);
            System.out.println("Sender : " + a + " " + context);
            this.rabbitTemplate.convertAndSend(ParamConstant.QUEUE_NAME, a + " : " + context);
        });
        return "success";
    }
}
