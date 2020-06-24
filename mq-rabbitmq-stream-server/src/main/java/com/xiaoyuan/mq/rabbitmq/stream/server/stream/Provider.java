package com.xiaoyuan.mq.rabbitmq.stream.server.stream;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Administrator
 * @description EnableBinding定义消息的推送管道
 */
@RestController
@EnableBinding(Source.class)
public class Provider {

    /**
     * 消息发送管道
     */
    @Resource
    private MessageChannel output;

    @GetMapping("/777")
    public String send(int i) {
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String context = "hello " + date;
        System.out.println("Sender : " + i +" "+ context);
        //简单对列的情况下routingKey即为Q名
        output.send(MessageBuilder.withPayload(context).build());
        return "success";
    }
}
