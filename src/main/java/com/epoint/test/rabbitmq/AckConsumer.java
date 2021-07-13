package com.epoint.test.rabbitmq;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

/**
 * Description:
 *
 * @author james
 * @date 2021/7/13 14:50
 */
@Slf4j
@Component
public class AckConsumer implements ChannelAwareMessageListener
{
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        // 消息的唯一性ID
        long deliveryTag = message.getMessageProperties().getDeliveryTag();

        try {
            String msg = message.toString();
            log.info("消息: " + msg);
            log.info("消息来自: " + message.getMessageProperties().getConsumerQueue());

            // 手动确认
            channel.basicAck(deliveryTag, true);
        }
        catch (Exception e) {
            // 拒绝策略
            channel.basicReject(deliveryTag, false);
            e.printStackTrace();
        }
    }
}
