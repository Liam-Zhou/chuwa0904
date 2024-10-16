package com.chuwa.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class KafkaProducerService {

    @Value("${kafka.topic.name}")
    private String topicName;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Transactional
    public void sendMessage(String key, String message) {
        kafkaTemplate.send(topicName, key, message);
    }

}
