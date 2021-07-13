package com.epoint.test.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description: 扇形交换机，接收到消息后会将消息转发到所有队列，类似发布/广播模式
 *
 * @author james
 * @date 2021/7/13 14:26
 */
@Configuration
public class FanoutRabbitMQConfig
{
    public static final String QUEUE_A = "fanoutQueueA";
    public static final String QUEUE_B = "fanoutQueueB";
    public static final String QUEUE_C = "fanoutQueueC";
    public static final String EXCHANGE = "fanoutExchange";

    // ----- 交换机 -----
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(EXCHANGE, true, false);
    }

    // ----- 队列 -----
    @Bean
    public Queue fanoutQueueA() {
        return new Queue(QUEUE_A, true);
    }

    @Bean
    public Queue fanoutQueueB() {
        return new Queue(QUEUE_B, true);
    }

    @Bean
    public Queue fanoutQueueC() {
        return new Queue(QUEUE_C, true);
    }

    // ----- 绑定 -----
    @Bean
    public Binding bindingFanoutA() {
        return BindingBuilder.bind(fanoutQueueA()).to(fanoutExchange());
    }

    @Bean
    public Binding bindingFanoutB() {
        return BindingBuilder.bind(fanoutQueueB()).to(fanoutExchange());
    }

    @Bean
    public Binding bindingFanoutC() {
        return BindingBuilder.bind(fanoutQueueC()).to(fanoutExchange());
    }
}
