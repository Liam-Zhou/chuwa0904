package com.chuwa.demo.service;


import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;


@Service
public class KafkaConsumerService {
    @Value("${spring.kafka.consumer.group-id}")
    private String consumerGroupId;
    @Value("${kafka.topic.name}")
    private String topic;


    @KafkaListener(topics = "${kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}", concurrency = "3")
    public void listenGroupFoo(ConsumerRecord<String, String> record, Acknowledgment acknowledgment) {
        String consumerId = record.partition() + "-" + record.offset(); // Derive consumer identity
        try {
            Thread.sleep(1000);  // Simulating long processing
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(record.value().equals("message-3") ) {   
            System.err.println("Message3 is received but not respond");
            return;
            //throw new RuntimeException("Failing on purpose");
        }
        System.out.println("Received message: " + record.value() 
                + " from group: " + consumerGroupId 
                + " with topic: " + topic 
                + " partition: " + record.partition() 
                + " offset: " + record.offset()
                + " topic id: " + consumerId);
        acknowledgment.acknowledge();
    }

}
