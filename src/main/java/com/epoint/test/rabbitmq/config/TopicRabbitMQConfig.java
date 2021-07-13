package com.epoint.test.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description: 支持binding key和routing key的模糊匹配，会把消息路由到满足条件的Queue
 *
 * @author james
 * @date 2021/7/13 14:33
 */
@Configuration
public class TopicRabbitMQConfig
{
    public static final String QUEUE_1 = "topicQueue1";
    public static final String QUEUE_2 = "topicQueue2";
    public static final String EXCHANGE = "topicExchange";
    public static final String ROUTING_01 = "topic.01";
    public static final String ROUTING_ALL = "topic.#";

    // 交换机
    @Bean
    public TopicExchange myTopicExchange() {
        return new TopicExchange(EXCHANGE, true, false);
    }

    // ----- 队列 -----
    @Bean
    public Queue myTopicQueue1() {
        return new Queue(QUEUE_1, true);
    }

    @Bean
    Queue myTopicQueue2() {
        return new Queue(QUEUE_2, true);
    }

    /**
     * 绑定路由键为topic.01
     */
    @Bean
    public Binding binding1() {
        return BindingBuilder.bind(myTopicQueue1()).to(myTopicExchange()).with(ROUTING_01);
    }

    /**
     * 绑定路由键为topic.#规则
     */
    @Bean
    public Binding binding2() {
        return BindingBuilder.bind(myTopicQueue2()).to(myTopicExchange()).with(ROUTING_ALL);
    }

}
