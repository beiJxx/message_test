package com.epoint.test.kafka;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * Description:
 *
 * @author james
 * @date 2021/7/13 11:00
 */
@Slf4j
@Component
public class KafkaProducer
{
    public static final String TOPIC_TEST1 = "topic_test1";
    public static final String GROUP_TEST1 = "group_test1";

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public String sendMsg1(String message) {
        //发送消息
        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(TOPIC_TEST1, message);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>()
        {
            @Override
            public void onFailure(@NotNull Throwable throwable) {
                //发送失败的处理
                log.info(TOPIC_TEST1 + " - 生产者 发送消息失败：" + throwable.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, Object> stringObjectSendResult) {
                //成功的处理
                log.info(TOPIC_TEST1 + " - 生产者 发送消息成功：" + stringObjectSendResult.toString());
            }
        });
        return "success";
    }
}
