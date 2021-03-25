package com.xiaoyuan.mq.rabbitmq.base.dlx;

/**
 * @author liyuan
 * @description 延时队列: 无消费者的普通队列+死信队列
 */
public interface ParamConstant {

    static final String EXCHANGE_NAME = "test_ttl_exchange";

    static final String ROUTING_KEY = "test_ttl_routing_key";

    static final String QUEUE_NAME = "test_ttl_queue";


    static final String DELAY_EXCHANGE_NAME = "test_delay_exchange";

    static final String DELAY_ROUTING_KEY = "test_delay_routing_key";

    static final String DELAY_QUEUE_NAME = "test_delay_queue";

}
