package com.chuwa.demo.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "${kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void listenGroup1(String message) {
        System.out.println("Received message: " + message + " from group: consumer_group_1");
    }

    @KafkaListener(topics = "${kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id-2}")
    public void listenGroup2(String message) {
        System.out.println("Received message: " + message + " from group: consumer_group_2");
    }
}
