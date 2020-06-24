package com.xiaoyuan.mq.rabbitmq.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @description rabbitmq 基本使用,直接启动对应包下的main方法
 * @author liyuan
 */
@SpringBootApplication
public class MqRabbitmqBaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(MqRabbitmqBaseApplication.class, args);
    }

}
