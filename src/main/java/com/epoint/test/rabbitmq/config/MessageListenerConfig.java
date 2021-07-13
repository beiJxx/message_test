package com.epoint.test.rabbitmq.config;

import com.epoint.test.rabbitmq.AckConsumer;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description:
 *
 * @author james
 * @date 2021/7/13 14:50
 */
@Configuration
public class MessageListenerConfig
{
    @Autowired
    private CachingConnectionFactory cachingConnectionFactory;

    @Autowired
    private AckConsumer ackConsumer;

    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(cachingConnectionFactory);

        // 监听队列名
        container.setQueueNames(AckRabbitMQConfig.QUEUE);
        // 当前消费者数量
        container.setConcurrentConsumers(1);
        // 最大消费者数量
        container.setMaxConcurrentConsumers(1);
        // 手动确认
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        // 设置监听器
        container.setMessageListener(ackConsumer);

        return container;
    }
}
