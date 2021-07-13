package com.epoint.test.controller;

import com.epoint.test.kafka.KafkaProducer;
import com.epoint.test.rabbitmq.RabbitMQProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 *
 * @author james
 * @date 2021/7/13 10:53
 */
@Slf4j
@RestController
public class TestController
{

    @Autowired
    private KafkaProducer kafkaProducer;

    @GetMapping("/kafka/{message}")
    public String kafka(@PathVariable("message") String message) {
        log.info("kafka ===> message:{}", message);
        return kafkaProducer.sendMsg1(message);
    }

    @Autowired
    private RabbitMQProducer rabbitMQProducer;

    @GetMapping("/rabbit/direct/{message}")
    public String rabbitDirect(@PathVariable("message") String message) {
        log.info("rabbit.direct ===> message:{}", message);
        rabbitMQProducer.sendDirect(message);
        return "success";
    }

    @GetMapping("/rabbit/fanout/{message}")
    public String rabbitFanout(@PathVariable("message") String message) {
        log.info("rabbit.fanout ===> message:{}", message);
        rabbitMQProducer.sendFanout(message);
        return "success";
    }

    @GetMapping("/rabbit/topic/{message}")
    public String rabbitTopic(@PathVariable("message") String message) {
        log.info("rabbit.topic ===> message:{}", message);
        rabbitMQProducer.sendTopic(message);
        return "success";
    }

    @GetMapping("/rabbit/directack/{message}")
    public String rabbitDirectAck(@PathVariable("message") String message) {
        log.info("rabbit.directack ===> message:{}", message);
        rabbitMQProducer.sendDirectAck(message);
        return "success";
    }
}
