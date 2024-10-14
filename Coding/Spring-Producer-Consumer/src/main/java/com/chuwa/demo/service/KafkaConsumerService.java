package com.chuwa.demo.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class KafkaConsumerService {
    @Value("${spring.kafka.consumer.group-id}")
    private String consumerGroupId;
    @Value("${kafka.topic.name}")
    private String topic;

    @KafkaListener(topics = "${kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void listenGroupFoo(String message) {
        //System.out.println("Received message: " + message + " from group: " + consumerGroupId + " with topic: " + topic);

        System.out.println("Consumer 1 Received message: " + message +
                " from group: " + consumerGroupId +
                " with topic: " + topic +
                " processed by thread: " + Thread.currentThread().getName());
    }

    @KafkaListener(topics = "${kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void listenGroupFoo2(String message) {
        //System.out.println("Received message: " + message + " from group: " + consumerGroupId + " with topic: " + topic);

        System.out.println("Consumer 2 Received message: " + message +
                " from group: " + consumerGroupId +
                " with topic: " + topic +
                " processed by thread: " + Thread.currentThread().getName());
    }


    @KafkaListener(topics = "${kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void listenGroupFoo3(String message) {
        //System.out.println("Received message: " + message + " from group: " + consumerGroupId + " with topic: " + topic);

        System.out.println("Consumer 3 Received message: " + message +
                " from group: " + consumerGroupId +
                " with topic: " + topic +
                " processed by thread: " + Thread.currentThread().getName());
    }

    @KafkaListener(topics = "${kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void listenGroupFoo4(String message) {
        //System.out.println("Received message: " + message + " from group: " + consumerGroupId + " with topic: " + topic);

        System.out.println("Consumer 4 Received message: " + message +
                " from group: " + consumerGroupId +
                " with topic: " + topic +
                " processed by thread: " + Thread.currentThread().getName());
    }

    @KafkaListener(topics = "${kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void listenGroupFoo5(String message) {
        //System.out.println("Received message: " + message + " from group: " + consumerGroupId + " with topic: " + topic);

        System.out.println("Consumer 4 Received message: " + message +
                " from group: " + consumerGroupId +
                " with topic: " + topic +
                " processed by thread: " + Thread.currentThread().getName());
    }

    // Second consumer group, use the second factory
    @KafkaListener(topics = "${kafka.topic.name}",  groupId = "${spring.kafka.consumer.group-id-2}")
    public void listenGroup2(String message) {
        System.out.println("Consumer Group 2 Received: " + message);
    }







}
