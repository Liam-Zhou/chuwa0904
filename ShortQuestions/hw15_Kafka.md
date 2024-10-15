
# HW15 Kafka

## Explanation of Kafka Concepts

### Topic
A topic in Kafka is a stream of records (messages). Topics are divided into partitions for better parallelism. Consumers read data from these topics.

### Partition
Each topic is divided into partitions, where records are stored in a sequential order. Partitions allow Kafka to distribute data and balance load across multiple brokers.

### Broker
A Kafka broker is a server that stores data and serves clients (producers and consumers). Brokers work together to form a Kafka cluster, handling data replication and fault tolerance.

### Consumer Group
A group of consumers that work together to consume messages from topics. Each consumer in the group reads from different partitions of the same topic.

### Producer
A producer writes records to Kafka topics. Producers send data to specific partitions within a topic.

### Offset
The offset is a unique identifier assigned to each message within a partition, allowing consumers to keep track of which messages have been read.

### Zookeeper
Zookeeper coordinates and manages the Kafka cluster by maintaining metadata like broker information, partition information, and consumer group details.

## Kafka Questions

### 1. Given N (number of partitions) and M (number of consumers), what will happen when N >= M and N < M respectively?
- If **N >= M**, each consumer will read from one or more partitions. Load balancing is optimized.
- If **N < M**, some consumers will remain idle as there won’t be enough partitions for each consumer.

### 2. Explain how brokers work with topics.
Brokers store partitions of topics. Each broker handles requests for its partitions and coordinates with other brokers to replicate data across the Kafka cluster.

### 3. Are messages pushed to consumers or do consumers pull messages from topics?
Kafka uses a pull-based model. Consumers pull messages from topics at their own pace.

### 4. How to avoid duplicate consumption of messages?
Consumers can avoid duplicate consumption by keeping track of the offsets for the messages they have consumed, and Kafka provides consumer groups to manage this automatically.

### 5. What will happen if some consumers are down in a consumer group? Will data loss occur? Why?
The remaining consumers in the group will continue to consume messages. No data loss occurs since the messages are stored in Kafka until consumed.

### 6. What will happen if an entire consumer group is down? Will data loss occur? Why?
Messages are not lost because Kafka retains the data until it is consumed. The consumer group can resume consuming from the last committed offset.

### 7. Explain consumer lag and how to resolve it.
Consumer lag occurs when the consumer is reading messages slower than the producer is producing them. It can be resolved by increasing the number of consumers or optimizing consumer throughput.

### 8. Explain how Kafka tracks message delivery.
Kafka tracks message delivery using consumer offsets. Each consumer's progress is stored in Zookeeper (or Kafka’s internal system).

### 9. Compare Kafka vs RabbitMQ and compare messaging frameworks vs MySQL. Why Kafka?
- **Kafka vs RabbitMQ**: Kafka is better suited for high throughput and large-scale data streaming, while RabbitMQ is often used for real-time message queuing and low-latency tasks.
- **Kafka vs MySQL**: Kafka is designed for streaming data, while MySQL is a traditional database management system. Kafka excels in distributed data pipelines and event-driven architectures.

### 10.  On top of https://github.com/CTYue/Spring-Producer-Consumer

- Write a consumer application with Spring Kafka dependency, set up 3 consumers in a single consumer group, and prove message consumption with screenshots.
- Increase the number of consumers in a single consumer group, observe what happens, and explain your observation.
 
- Create multiple consumer groups using Spring Kafka, set up different numbers of consumers within each group, and observe the consumer offset.
- Prove that each consumer group is consuming messages on topics as expected with screenshots of offset records.
- Demonstrate different message delivery guarantees in Kafka using code or configuration changes.

![10.1](./hw15%20pic/10.1.png)


Increasing the number of consumers beyond the number of partitions in a Kafka consumer group results in underutilization of consumers, with only a limited number actively participating in message processing. This observation underscores the importance of aligning the number of partitions with the anticipated consumer count to optimize throughput and resource utilization.

Still working on the coding part.