package com.xiaoyuan.mq.rabbitmq.stream.server.simple;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author Administrator
 * @description EnableBinding定义消息的推送管道
 */
@RestController
@EnableBinding(Source.class)
public class SimpleProvider {

    /**
     * 消息发送管道
     */
    @Resource
    private MessageChannel output;

    @GetMapping("/simple")
    public String send(int i) {
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String context = "hello " + date;
        //简单对列的情况下routingKey即为Q名
        new Random().ints().limit(i).forEach(a -> {
            System.out.println("Sender : " + a + " " + context);
            output.send(MessageBuilder.withPayload( a + " : " +context).build());
        });
        return "success";
    }
}
