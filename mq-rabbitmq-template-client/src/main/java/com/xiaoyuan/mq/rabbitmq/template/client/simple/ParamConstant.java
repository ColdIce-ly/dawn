package com.xiaoyuan.mq.rabbitmq.template.client.simple;

/**
 * @author liyuan
 * @description RabbitListener 和 RabbitHandler 注解的使用
 * '@RabbitListener' 可以标注在类上面，需配合 @RabbitHandler 注解一起使用
 * '@RabbitListener' 标注在类上面表示当有收到消息的时候，就交给 @RabbitHandler 的方法处理，具体使用哪个方法处理，根据 MessageConverter 转换后的参数类型
 */
public interface ParamConstant {

    static final String QUEUE_NAME = "test_template_simple";

}
