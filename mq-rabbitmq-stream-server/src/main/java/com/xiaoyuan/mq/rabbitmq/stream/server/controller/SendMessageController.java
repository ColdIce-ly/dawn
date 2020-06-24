package com.xiaoyuan.mq.rabbitmq.stream.server.controller;

import com.xiaoyuan.mq.rabbitmq.stream.server.service.IMessageProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author EiletXie
 * @Since 2020/3/14 14:21
 */
@RestController
public class SendMessageController {

    @Resource
    private IMessageProvider messageProvider;

    @GetMapping("/sendMessage")
    public String sendMessage() {
        return messageProvider.send();
    }
}
