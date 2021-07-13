package com.epoint.test.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Description:
 *
 * @author james
 * @date 2021/7/13 11:02
 */
@Slf4j
@Component
public class KafkaConsumer
{
    @KafkaListener(topics = KafkaProducer.TOPIC_TEST1, groupId = KafkaProducer.GROUP_TEST1)
    public void listener1(ConsumerRecord<?, ?> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        Optional message = Optional.ofNullable(record.value());
        if (message.isPresent()) {
            Object msg = message.get();
            log.info("listener1 消费了： Topic:{}, Message:{}, offset:{}, partition:{}", topic, msg, record.offset(), record.partition());
            //手动提交
            ack.acknowledge();
        }
    }

}
