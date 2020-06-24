package com.xiaoyuan.mq.rabbitmq.template.client.work;


import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * @author liyuan
 * @description work工作队列
 * 一:轮询分发
 * 1、消费者1和消费者2获取到的消息内容是不同的，同一个消息只能被一个消费者获取。
 * 2、消费者1和消费者2获取到的消息的数量是相同的，一个是消费奇数号消息，一个是偶数。
 * 二:公平分发(能者多劳模式)
 * 1、根据消费的时间去分发,谁消费结束直接再次发送给他
 */
@Component
public class WorkConsumer2 {

    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = com.xiaoyuan.mq.rabbitmq.template.client.work.ParamConstant.QUEUE_NAME, durable = "true"), exchange = @Exchange(name = ParamConstant.EXCHANGE_NAME)))
    @RabbitHandler
    public void process(@Payload String hello, @Headers Map<String, Object> headers, Channel channel) throws IOException, InterruptedException {
        Thread.sleep(1000);
        System.out.println("SimpleConsumer2  : " + hello);
        /**
         * Delivery Tag 用来标识信道中投递的消息。RabbitMQ 推送消息给 Consumer 时，会附带一个 Delivery Tag，
         * 以便 Consumer 可以在消息确认时告诉 RabbitMQ 到底是哪条消息被确认了。
         * RabbitMQ 保证在每个信道中，每条消息的 Delivery Tag 从 1 开始递增。
         */
        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);

        /**
         *  multiple 取值为 false 时，表示通知 RabbitMQ 当前消息被确认
         *  如果为 true，则额外将比第一个参数指定的 delivery tag 小的消息一并确认
         */
        boolean multiple = false;

        //ACK,确认一条消息已经被消费
        channel.basicAck(deliveryTag, multiple);
    }
}
