package com.xiaoyuan.mq.rabbitmq.stream.client.copy.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author EiletXie
 * @Since 2020/3/14 15:43
 */
@Component
@EnableBinding(Sink.class)
public class ReceiverMessageListenerController {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    @Value("${server.port}")
    private  String serverPort;

    @StreamListener(Sink.INPUT)
    public void input(Message<String> message) {
        System.out.println("消费者2号， -----> 接受到的消息： " + message.getPayload()
                + "\t port: " + serverPort);
        System.out.println("2号累计消费{}次-------------" + atomicInteger.incrementAndGet());
    }
}