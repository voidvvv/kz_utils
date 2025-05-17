package com.kz.web.config.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MyListener {

    @KafkaListener(topics = {"test-topic"})
    public void listener(ConsumerRecord record) {
        Object key = record.key();
        Object value = record.value();
        String topic = record.topic();
        log.info("key: {}, value: {}, topic: {}", key, value, topic);
    }
}
