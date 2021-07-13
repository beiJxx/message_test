package com.epoint.test.rabbitmq;

import com.epoint.test.rabbitmq.config.DirectRabbitMQConfig;
import com.epoint.test.rabbitmq.config.FanoutRabbitMQConfig;
import com.epoint.test.rabbitmq.config.TopicRabbitMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Description:
 *
 * @author james
 * @date 2021/7/13 13:58
 */
@Slf4j
@Component
public class RabbitMQConsumer
{

    //Direct
    @RabbitHandler
    @RabbitListener(queues = DirectRabbitMQConfig.QUEUE)
    public void directListener(String message) {
        log.info("directListener:{}", message);
    }

    //Fanout
    @RabbitHandler
    @RabbitListener(queues = FanoutRabbitMQConfig.QUEUE_A)
    public void fanoutListenerA(String message) {
        log.info("fanoutListenerA:{}", message);
    }

    @RabbitHandler
    @RabbitListener(queues = FanoutRabbitMQConfig.QUEUE_B)
    public void fanoutListenerB(String message) {
        log.info("fanoutListenerB:{}", message);
    }

    @RabbitHandler
    @RabbitListener(queues = FanoutRabbitMQConfig.QUEUE_C)
    public void fanoutListenerC(String message) {
        log.info("fanoutListenerC:{}", message);
    }

    //Topic
    @RabbitHandler
    @RabbitListener(queues = TopicRabbitMQConfig.QUEUE_1)
    public void topicListener1(String message) {
        log.info("topicListener1:{}", message);
    }

    @RabbitHandler
    @RabbitListener(queues = TopicRabbitMQConfig.QUEUE_2)
    public void topicListener2(String message) {
        log.info("topicListener2:{}", message);
    }

}
