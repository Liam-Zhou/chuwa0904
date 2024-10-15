package com.chuwa.demo.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class KafkaConsumerService {
    @Value("consumer_group_1")
    private String consumerGroupId1;
    @Value("consumer_group_2")
    private String consumerGroupId2;
    @Value("${kafka.topic.name}")
    private String topic;

    @KafkaListener(topics = "chuwa-yyds", groupId = "consumer_group_1", concurrency = "3")
    public void listenGroupFoo(String message) {
        System.out.println("Received message: " + message + " from group: " + consumerGroupId1 + " with topic: " + topic);
    }

    @KafkaListener(topics = "chuwa-yyds", groupId = "consumer_group_2", concurrency = "2")
    public void listenGroupFoo1(String message) {
        System.out.println("Received message: " + message + " from group: " + consumerGroupId2 + " with topic: " + topic);
    }


}
