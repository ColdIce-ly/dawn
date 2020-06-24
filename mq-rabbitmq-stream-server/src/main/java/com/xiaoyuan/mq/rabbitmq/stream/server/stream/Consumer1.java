package com.xiaoyuan.mq.rabbitmq.stream.server.stream;


import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * @description 监听q_hello队列
 * @author Administrator
 */
@Component
@EnableBinding(Sink.class)
public class Consumer1 {

    @StreamListener(Sink.INPUT)
    public void input(Message<String> message) {
        System.out.println("消费者1号， -----> 接受到的消息： " + message.getPayload());
    }
}
