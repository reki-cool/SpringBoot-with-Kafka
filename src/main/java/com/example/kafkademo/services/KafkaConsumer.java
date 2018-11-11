package com.example.kafkademo.services;

import com.example.kafkademo.storage.MessageStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
    private static final Logger log = LoggerFactory.getLogger(KafkaProducer.class);

    @Autowired
    MessageStorage storage;

    @KafkaListener(topics="${kafka-demo.kafka.topic}")
    public void processMessage(String content) {
        log.info("received content = '{}'", content);
        storage.put(content);
    }
}