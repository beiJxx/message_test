package com.epoint.test.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description: 直连型交换机，根据消息携带的路由键，将消息转发给对应的队列
 *
 * @author james
 * @date 2021/7/13 14:20
 */
@Configuration
public class DirectRabbitMQConfig
{
    public static final String QUEUE = "directQueue";
    public static final String EXCHANGE = "directExchange";
    public static final String ROUTING = "direct.routing";

    /**
     * 交换机
     */
    @Bean
    public DirectExchange myDirectExchange() {
        // 参数意义:
        // name: 名称
        // durable: true
        // autoDelete: 自动删除
        return new DirectExchange(EXCHANGE, true, false);
    }

    /**
     * 队列
     */
    @Bean
    public Queue myDirectQueue() {
        return new Queue(QUEUE, true);
    }

    /**
     * 绑定
     */
    @Bean
    public Binding bindingDirect() {
        return BindingBuilder
                .bind(myDirectQueue())
                .to(myDirectExchange())
                .with(ROUTING);
    }
}
