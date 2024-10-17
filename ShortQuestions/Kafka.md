# Kafka

## Explain following concepts, and how they coordinate with each other:
- **Topic**: A topic is a logical channel where producers send messages, and consumers read messages. Topics are subdivided into partitions.

- **Partition**: A topic can have multiple partitions, allowing parallelism. Each partition stores messages in a sequence, ensuring order within a partition but not across partitions.

- **Broker**: A Kafka broker is a server that stores data and serves consumers/producers. Brokers host partitions of topics and manage their replication.

- **Consumer Group**: A consumer group is a set of consumers that work together to consume messages from a topic. Each partition is consumed by only one consumer within a group.

- **Producer**: A producer sends (publishes) messages to a Kafka topic. It can distribute messages across partitions using strategies like round-robin or key-based routing.

- **Offset**: An offset is the unique ID of a message in a partition. Consumers use offsets to track their position in the log and resume from where they left off.

- **Zookeeper**: Zookeeper manages Kafka’s metadata, such as broker information and leader election for partitions. It coordinates the brokers in the Kafka cluster.

---

## Answer following questions:

### 1. Given N (number of partitions) and M (number of consumers):
- **When N >= M**: Each consumer will read from one or more partitions. Some partitions might be consumed by the same consumer.
- **When N < M**: Some consumers will remain idle, as each partition can only be read by one consumer within the same group.

### 2. Explain how brokers work with topics?
Brokers store partitions of topics. Each broker may store multiple partitions of different topics, and they manage the replication of these partitions across other brokers for fault tolerance.

### 3. Are messages pushed to consumers or consumers pull messages from topics?
Consumers pull messages from topics. They request messages by specifying the offset they want to read from.

### 4. How to avoid duplicate consumption of messages?
To avoid duplicate consumption:
- Use **"Exactly Once Semantics" (EOS)** with transactional producers.
- Consumers should commit their offsets after successfully processing messages.

### 5. What will happen if some consumers are down in a consumer group? Will data loss occur? Why?
No data loss will occur. The partitions assigned to the down consumers will be reassigned to other active consumers in the group, ensuring all partitions are consumed.

### 6. What will happen if an entire consumer group is down? Will data loss occur? Why?
No data loss occurs. Kafka retains messages in the partitions based on the topic's retention policy. When the consumer group is back up, they can resume from the last committed offsets.

### 7. Explain consumer lag and how to resolve it?
**Consumer lag** occurs when a consumer is not keeping up with the production rate, leading to unprocessed messages. To resolve lag:
- Scale the number of consumers.
- Improve consumer processing efficiency.

### 8. Explain how Kafka tracks message delivery?
Kafka tracks message delivery using offsets. Each consumer in a group commits its processed message offsets to Kafka. Consumers can resume from the last committed offset after a failure.

### 9. Compare Kafka vs RabbitMQ, and messaging frameworks vs MySQL:
- **Kafka vs RabbitMQ**:
    - Kafka is designed for high-throughput, distributed, real-time data streaming with log-based storage.
    - RabbitMQ is more suitable for low-latency message delivery and complex routing but may not scale as easily as Kafka for massive data streams.

- **Messaging frameworks vs MySQL**:
    - Messaging frameworks (like Kafka) decouple producers and consumers, offering asynchronous processing, fault tolerance, and high availability.
    - MySQL (or traditional databases) is built for synchronous, transactional workloads and is not designed for high-throughput streaming or decoupled architectures. Kafka is better for distributed log-based message storage and real-time data streaming.