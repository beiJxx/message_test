package com.epoint.test.rabbitmq;

import com.epoint.test.rabbitmq.config.AckRabbitMQConfig;
import com.epoint.test.rabbitmq.config.DirectRabbitMQConfig;
import com.epoint.test.rabbitmq.config.FanoutRabbitMQConfig;
import com.epoint.test.rabbitmq.config.TopicRabbitMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Description:
 *
 * @author james
 * @date 2021/7/13 13:44
 */
@Slf4j
@Component
public class RabbitMQProducer
{
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendDirect(String message) {
        rabbitTemplate.convertAndSend(DirectRabbitMQConfig.EXCHANGE, DirectRabbitMQConfig.ROUTING, message);
        log.info("sendDirect 生产者 queue:{} 发送消息成功：{}", DirectRabbitMQConfig.QUEUE, message);
    }

    public void sendFanout(String message) {
        rabbitTemplate.convertAndSend(FanoutRabbitMQConfig.EXCHANGE, null, message);
        log.info("sendFanout 生产者 queue:{},{},{} 发送消息成功：{}", FanoutRabbitMQConfig.QUEUE_A, FanoutRabbitMQConfig.QUEUE_B, FanoutRabbitMQConfig.QUEUE_C, message);
    }

    public void sendTopic(String message) {
        String message1 = message + " topic.01";
        rabbitTemplate.convertAndSend(TopicRabbitMQConfig.EXCHANGE, TopicRabbitMQConfig.ROUTING_01, message1);
        log.info("sendTopic 生产者 queue:{} 发送消息成功：{}", TopicRabbitMQConfig.QUEUE_1, message1);
        String message2 = message + " topic.xxx";
        rabbitTemplate.convertAndSend(TopicRabbitMQConfig.EXCHANGE, TopicRabbitMQConfig.ROUTING_ALL, message2);
        log.info("sendTopic 生产者 queue:{} 发送消息成功：{}", TopicRabbitMQConfig.QUEUE_2, message2);
    }

    public void sendDirectAck(String message) {
        rabbitTemplate.convertAndSend(AckRabbitMQConfig.EXCHANGE, AckRabbitMQConfig.ROUTING, message);
        log.info("sendDirectAck 生产者 queue:{} 发送消息成功：{}", AckRabbitMQConfig.QUEUE, message);
    }

}
