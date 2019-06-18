package com.example.kaffkaDemo.KafkaReceiver;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author chenjunhua
 * @title: KafkaReceiver
 * @projectName mapper-renren
 * @description: TODO
 * @date 2019/6/1018:13
 */
@Component
public class KafkaReceiver {
    private Logger logger = LoggerFactory.getLogger(KafkaReceiver.class);
    /**
     * 监听cva_contact_info topic
     *
     * @param record
     * @param topic  topic
     */
    @KafkaListener(topics = "cva_contact_info")
    public void listenCvaContactInfo(ConsumerRecord<?, ?> record, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        //判断是否NULL
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        System.out.println(kafkaMessage);
        if (kafkaMessage.isPresent()) {
            //获取消息
            String message = (String)kafkaMessage.get();

            logger.info("Receive： +++++++++++++++ Topic:" + topic);
            logger.info("Receive： +++++++++++++++ Record:" + record);
            logger.info("Receive： +++++++++++++++ Message:" + message);

        }
    }

}
