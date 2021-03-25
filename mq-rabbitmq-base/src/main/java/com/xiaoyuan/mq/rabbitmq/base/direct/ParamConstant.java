package com.xiaoyuan.mq.rabbitmq.base.direct;

/**
 * @author liyuan
 * @description 直接模式
 * 可以具备多个接受规则
 * 满足条件就会接受消息,不满足都不会接受,消息丢失,消息会消费多次
 */
public interface ParamConstant {

    static final String EXCHANGE_TYPE = "direct";

    static final String EXCHANGE_NAME = "test_exchange_direct";

    static final String QUEUE_NAME_CACHE = "test_queue_direct_cache";

    static final String QUEUE_NAME_ES = "test_queue_direct_es";

}
