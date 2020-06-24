package com.xiaoyuan.mq.rabbitmq.template.client.simple;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @description 监听q_hello队列
 * @author Administrator
 */
@Component
public class SimpleConsumer2 {

    @RabbitListener(queues = ParamConstant.QUEUE_NAME)
    public void process(String hello) {
        System.out.println("SimpleConsumer2  : " + hello);
    }

}
