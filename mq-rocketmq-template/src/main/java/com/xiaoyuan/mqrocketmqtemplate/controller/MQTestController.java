package com.xiaoyuan.mqrocketmqtemplate.controller;


import com.xiaoyuan.mqrocketmqtemplate.basic.SpringProducer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ：
 * @date ：Created in 2020/10/27
 * @description:
 **/
@RestController
public class MQTestController {

    private final String topic = "marketing_cloud_delay_dev";

    @Resource
    private SpringProducer producer;

    @GetMapping("/sendMessage")
    public String sendMessage(String message) {
        producer.sendMessage(topic, message);
        return "消息发送完成";
    }

    /**
     * @param message
     * @return
     * @throws InterruptedException
     * @description 这个发送事务消息的例子中有很多问题，需要注意下
     */
    @GetMapping("/sendTransactionMessage")
    public String sendTransactionMessage(String message) throws InterruptedException {
        producer.sendMessageInTransaction(topic, message);
        return "消息发送完成";
    }
}