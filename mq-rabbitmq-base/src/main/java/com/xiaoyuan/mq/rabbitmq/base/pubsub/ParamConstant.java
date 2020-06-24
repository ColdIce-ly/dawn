package com.xiaoyuan.mq.rabbitmq.base.pubsub;

/**
 * @author liyuan
 * @description 分裂模式;订阅发布者模式
 * 只要路由键一样就可以直接消费
 * 满足条件就会接受消息,不满足都不会接受,消息不会丢失,路由键可以存储消息,消息会消费多次
 */
public interface ParamConstant {

    static final String EXCHANGE_NAME = "test_exchange_fanout";

    static final String QUEUE_NAME_ONE = "test_queue_work1";

    static final String QUEUE_NAME_TWO = "test_queue_work2";

}
