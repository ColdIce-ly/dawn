package com.xiaoyuan.mq.rabbitmq.base.config;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * @author Administrator
 * @description 连接Channel
 * @date
 */
public final class RabbitMQConnection {

    private static final ConnectionFactory FACTORY;
    private static Connection connection;
    private static Channel channel;

    //    @Value("${spring:rabbitmq:host}")
    private static String host = "8.136.185.56";

    //    @Value("${spring:rabbitmq:port}")
    private static String port = "5672";

    //    @Value("${spring:rabbitmq:username}")
    private static String username = "guest";

    //    @Value("${spring:rabbitmq:password}")
    private static String password = "guest";

    //    @Value("${spring:rabbitmq:virtual-host}")
    private static String virtualHost = "/";


    static {
        FACTORY = new ConnectionFactory();
        FACTORY.setHost(host);
        FACTORY.setPort(Integer.parseInt(port));
        FACTORY.setUsername(username);
        FACTORY.setPassword(password);
        FACTORY.setVirtualHost(virtualHost);
        try {
            connection = FACTORY.newConnection();
            channel = connection.createChannel();
        } catch (IOException | TimeoutException ioException) {
            ioException.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public static Channel createChannel() {
        Channel connectionChannel = null;
        try {
            connectionChannel = connection.createChannel();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return connectionChannel;
    }

    /**
     * @throws IOException
     * @description 这个是申明交换器，如果没有申明就给默认队列的这个交换器，而且发送的类型默认是direct
     * $exchange 交换机名
     * $type  交换机类型，分别有direct、fanout、topic
     * $passsive  false 只判断不创建（一般用于判断该交换机是否存在），如果你希望查询交换机是否存在．而又不想在查询时创建这个交换机．设置此为true即可
     * 如果交换机不存在,则会抛出一个错误的异常.如果存在则返回NULL
     * $durable false 表示了如果MQ服务器重启,这个交换机是否要重新建立(如果设置为true则重启mq，该交换机还是存在，相当于持久化。)
     * 我们的案例代码有点类似于在服务器设立一个数据库内存表,并且每次访问都要判定内存表是否存在.
     * 而如果开启了这个属性,则相当于建了一个永久表.以后直接访问即可.不需要每次都判定是否存在.如同访问MYSQL。
     * $auto_delete true 无用自动销毁。如果绑定的所有队列都不在使用了.是否自动删除这个交换机.（比如设置为true,它绑定的对列全部被删除后，该交换器会被自动删除，）
     * $internal false 内部交换机.即不允许使用客户端推送消息.MQ内部可以让交换机作为一个队列绑定到另外一个交换机下.想想一下以太网的交换机就是了.所以开启这个属性,表示是一个他直接收其他交换机发来的信息
     * $nowait false 如果为True则表示不等待服务器回执信息.函数将返回NULL,可以提高访问速度..应用范围不确定
     * $arguments null 额外的一些参数,比如优先级,什么的.
     * $ticket null 未知
     */
    public static void exchangeDeclare() throws IOException {
        channel.exchangeDeclare("", "");
    }

    /**
     * @param queue      队列名.而存在默认值的意思是.你可以创建一个不重复名称的一个临时队列.(交换机没法创建临时的)
     * @param durable    false 持久化
     * @param exclusive  false 排他队列,如果你希望创建一个队列,并且只有你当前这个程序(或进程)进行消费处理.不希望别的客户端读取到这个队列.用这个方法甚好.而同时如果当进程断开连接.这个队列也会被销毁.不管是否设置了持久化或者自动删除.
     * @param autoDelete true 自动销毁（当最后一个消费者取消订阅时队列会自动移除，对于临时队列只有一个消费服务时适用，）
     * @param arguments
     * @throws IOException
     * @description 声明队列
     * $passsive false 只判断不创建(判断该队列是否存在) 只查询不创建.如果为true,如果存在这个队列,则会返回队列的信息.如果不存在这个队列..则会抛异常(与交换机不同的是,如果交换机判断存在,则返回NULL,否则异常)
     * $nowait false 执行后不需要等结果
     * $arguments null
     * $arguments = new AMQPTable([
     * 'x-message-ttl'          => 10000,  // 延迟时间 （毫秒）创建queue时设置该参数可指定消息在该queue中待多久，可根据x-dead-letter-routing-key和x-dead-letter-exchange生成可延迟的死信队列。
     * 'x-expires'              => 26000,  // 队列存活时间  如果一个队列开始没有设置存活时间，后面又设置是无效的。
     * 'x-dead-letter-exchange' => 'exchange_direct_ttl3',  // 延迟结束后指向交换机（死信收容交换机）
     * 'x-dead-letter-queue'    => 'queue_ttl3',  // 延迟结束后指向队列（死信收容队列）,
     * //'x-dead-letter-routing-key' => 'queue_ttl3',  // 设置routing-key
     * //'x-max-priority'=>'10' //声明优先级队列.表示队列应该支持的最大优先级。建议使用1到10之间.该参数会造成额外的CPU消耗。
     * ]
     * );
     */
    public static void queueDeclare(String queue, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments) throws IOException {
        channel.queueDeclare("queueName", false, false, false, null);
    }

    /**
     * @param queue      队列名
     * @param exchange   交换机名
     * @param routingKey 路由名（对应）
     * @param arguments  额外参数
     * @throws IOException
     * @description 绑定队列
     * $nowait 不等待执行结果
     */
    public static void queueBind(String queue, String exchange, String routingKey, Map<String, Object> arguments) throws IOException {
        channel.queueBind("queue_delete1", "exchange_delete1", "type");
    }

    /**
     * @throws IOException
     * @description 消费
     * queue 消息要取得消息的队列名
     * consumer_tag 消费者标签
     * no_local false 这个功能属于AMQP的标准,但是rabbitMQ并没有做实现.
     * no_ack(DeliverCallback) false 收到消息后,是否不需要回复确认即被认为被消费（在默认情况下，消息确认机制是关闭的。现在是时候开启消息确认机制，该参数设置为true,并且工作进程处理完消息后发送确认消息。）
     * exclusive false 排他消费者,即这个队列只能由一个消费者消费.适用于任务不允许进行并发处理的情况下.比如系统对接
     * nowait false 不返回执行结果,但是如果排他开启的话,则必须需要等待结果的,如果两个一起开就会报错
     * callback null 回调函数
     */
    public static void basicConsume() throws IOException {
        channel.basicConsume("", false, (consumerTag, delivery) -> {
        }, consumerTag -> {
        });
    }

}