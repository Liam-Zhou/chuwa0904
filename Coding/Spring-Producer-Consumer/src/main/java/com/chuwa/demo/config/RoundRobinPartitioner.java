package com.chuwa.demo.config;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

public class RoundRobinPartitioner implements Partitioner {

    private int counter = 0;

    @Override
    public void configure(Map<String, ?> configs) {
        
    }

    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        // Get the number of partitions for the topic
        int numPartitions = cluster.partitionCountForTopic(topic);

        // Round-robin logic: increment counter and modulo it by the number of partitions
        int partition = counter % numPartitions;
        counter++;
        counter%=10;
        return partition;
    }

    @Override
    public void close() {
    }
}
