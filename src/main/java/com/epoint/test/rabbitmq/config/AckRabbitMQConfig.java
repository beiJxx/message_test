package com.epoint.test.rabbitmq.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description: 手动确认配合直连型交换机
 *
 * @author james
 * @date 2021/7/13 14:47
 */
@Slf4j
@Configuration
public class AckRabbitMQConfig
{
    public static final String QUEUE = "ackQueue";
    public static final String EXCHANGE = "ackExchange";
    public static final String ROUTING = "ack.routing";

    /**
     * 交换机
     */
    @Bean
    public DirectExchange myDirectAckExchange() {
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
    public Queue myDirectAckQueue() {
        return new Queue(QUEUE, true);
    }

    /**
     * 绑定
     */
    @Bean
    public Binding bindingDirectAck() {
        return BindingBuilder
                .bind(myDirectAckQueue())
                .to(myDirectAckExchange())
                .with(ROUTING);
    }

    @Bean
    public RabbitTemplate createRabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);

        // 开启Mandatory, 才能触发回调函数，无论消息推送结果如何都强制调用回调函数
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            log.info("ConfirmCallback:  相关数据：{}", correlationData);
            log.info("ConfirmCallback:  确认情况：{}", ack);
            log.info("ConfirmCallback:  原因：{}", cause);
        });

        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            log.info("ReturnCallback: 消息：{}", message.toString());
            log.info("ReturnCallback: 回应码：{}", replyCode);
            log.info("ReturnCallback: 回应信息：{}", replyText);
            log.info("ReturnCallback: 交换机：{}", exchange);
            log.info("ReturnCallback: 路由键：{}", routingKey);
        });

        return rabbitTemplate;
    }
}
