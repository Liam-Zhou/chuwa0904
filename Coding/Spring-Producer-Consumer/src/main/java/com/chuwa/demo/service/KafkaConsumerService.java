package com.chuwa.demo.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class KafkaConsumerService {
    @Value("${spring.kafka.consumer.group-id}")
    private String consumerGroupId;
    @Value("${spring.kafka.consumer.group-id-2}")
    private String consumerGroupId2;
    @Value("${kafka.topic.name}")
    private String topic;

    @KafkaListener(topics = "${kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void listenGroupFoo(String message) {
        System.out.println("Received message: " + message + " from group: " + consumerGroupId + " with topic: " + topic);
    }
    @KafkaListener(topics = "${kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id-2}")
    public void listenGroupFoo2(String message) {
        System.out.println("Received message: " + message + " from group: " + consumerGroupId + " with topic: " + topic);
    }

}
