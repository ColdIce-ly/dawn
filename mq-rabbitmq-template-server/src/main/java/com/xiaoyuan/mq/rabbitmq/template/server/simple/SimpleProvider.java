package com.xiaoyuan.mq.rabbitmq.template.server.simple;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@RestController
public class SimpleProvider {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/simple")
    public String send(int i) {
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());//24小时制
        String context = "hello " + date;
        //简单对列的情况下routingKey即为Q名
        new Random().ints().limit(i).forEach(a -> {
            System.out.println("Sender : " + a + " " + context);
            this.rabbitTemplate.convertAndSend(ParamConstant.QUEUE_NAME, a + " : " + context);
        });
        return "success";
    }
}
