package com.xiaoyuan.mq.rabbitmq.base.topic;

/**
 * @author liyuan
 * @description 主题模式
 * # :匹配多个单词
 * * :匹配一个单词
 * 满足条件就会接受消息,不满足都不会接受,消息丢失,消息会消费多次
 */
public interface ParamConstant {

    static final String EXCHANGE_NAME = "test_exchange_topic";

    static final String QUEUE_NAME_ONE = "test_queue_topic_work_1";

    static final String QUEUE_NAME_TWO = "test_queue_topic_work_2";

}
