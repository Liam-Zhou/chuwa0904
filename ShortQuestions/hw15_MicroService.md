# hw15 MicroService

## Explain concepts

**Partition** is a mechanism splitting messages of a topic. Each topics can be divided into multiple partitions. Each partition storing a subset of the messages independently.

**Broker** is a instance of a server,  it receives, store and deliver messages. Kafka is composed of multiple brokers, each broker manages one or multiple partitions.

**Consumer group** is a logical set consists of multiple consumers who work at parallel, reading and handling the messages.

**Producer** sends the topics from the clients to partitions. It is the starting point of Kafka message stream.

**Offset** is the only integer or math digit used in Kafka. It marks the message position in each partition. Each partition has its own offset, all the message stores in order incrementally. Kafka read  and track message by the offset.

**Zookeeper** is a **distributed coordination service** that Kafka relies on to message **cluster** metadata and coordinate the operations of Kafka brokers. 

- Manages information about cluster's brokers. It tracks the status of each broker, when brokers join or fails. Zookeeper informs the other components to make adjustments accordingly. 



## 1. Given N (number of partitions) and M (number of consumers) what will happen when N>=M and N<M
When N >= M, each consumer will be assigned one or more partitions. 

Kafka ensures that each partition is consumed by only one consumer within the same consumer group. If number of partitions is greater than the number of consumers, some consumers then will be  assigned multiple partitions.

When N<M, some consumers will remain Idle. And the active consumers each will consume one partition.



## 2. Explain how brokers work with topics

简单来说 broker来给topic切片分配到不同分区，好处是负载均衡 提高容错和可用性

A **topic** is a logical channel to which messages are sent by producers and from which messages are consumed by consumers. 他表示一类数据的集合，举个例子我有一个 orders专门存订单相关的数据，或者有一个 logs主题来存储日志数据。

Producer发消息时候 它会发消息到特定的topic，分区是topic的物理切片，每个topic可以被分为多个分区，每个分区是独立的存储单元 他在kafka中是消息的存储和消费单位。回到相面的order例子 假设我有6个分区 我的topic是orders，producer发消息到orders这个topic上 然后这些消息被分配到不同的分区

- **Distribution**: Topics are divided into partitions to allow for parallel processing. When a topic is created (either manually or automatically), the Kafka broker stores metadata about the topic, such as the number of partitions, replication factor, and which brokers are responsible for each partition.
- Replication for fault tolerance
- Persist message, each message is assigned a unique offset within a partition, which consumers use to keep track of their progress.
- Serving consumers:
- Manage metadata for the cluster.



## 3. Are messages pushed to consumers or consumers pull messages from topics

Consumers **pull** message from topics.  

Consumer request -> broker responds back the messages not consumed based on the consumer's last committed offset. This gives the consumer control over how and when they consume messages.

Advantages: 

- Backpressure Control
- Batch processing
- Flexibility

The push model has **overloading** issues if the consumers consume slower than messages produced or pushed to them.



## 4. How to avoid duplicate consumption of message?

We use combination of mechanisms to ensure exactly-once or at-least-once delivery semantics, depending on use case. 

- Idempotent business logic, Programmer make sure user can not produce duplicated message. 
- Commit offsets carefully
  - Manually consumer commit offsets,  commit offset only after processing message
  - Avoid auto-commit

- **Idempotent** Consumers
  - meaning that processing the same message multiple times result in same outcome. Even the message is  consumed more than once, result remain consistent
  - For example: if consumer updates a database, use unique identifiers (e.g. message ID) to ensure each operations is only performed once.

- Transactional Processing (Exactly-Once semantics)
  - Transactional Producers, producers can write messages to multiple partitions atomically (either all writes  succeed or none do). Ensure partial write are avoided.
  - Transactional Consumers, consumers read and process messages within a transaction. They commit the read offsets and the processed result (e.g. writing to database) within the same transaction.
  - Enable transactions, enable transaction.id for producers and use kafka's transaction API to handle the consumer's read-process-write loop.

- Use Consumer Groups properly
  - Ensure each partition in a topic is consumed by only one consumer in a consumer group. 

- Avoid processing before offset commit
  - Ensure consumer process messages before committing the offset.

- Message deduplication
  - If messages have a unique identifier (such as a UUID or order ID), you can store these identifiers in a separate store (e.g., a database or cache) to ensure that each message is only processed once.
  - Can be used for idempotent operations, where you check if the message has been processed before proceeding.

- Handle consumer failures
  - If a consumer crashes or loses connection to the broker, it may end up reprocessing the last batch of messages. This is especially common if the consumer fails before committing offsets. To handle this:
    - Ensure that you commit offsets after successful processing.
    - Make your message processing **idempotent** if exactly-once processing is not feasible.
      - **Store Message Processing State Separately** (Use an External Store)
        - One common approach is to store the result of processing a message in an **external store** (like a database) that tracks whether each message has already been processed.
        - Each message should have a **unique identifier** (like an order ID, transaction ID, or message ID).
        - Before processing a message, check in the external store whether it has already been processed. If it has, you can safely skip it, ensuring idempotency.
      - **Process and Store State Atomically**
        - To avoid inconsistency, **processing the message** and **storing the result (or marking it as processed)** should be done **atomically** (as a single operation). This ensures that if your consumer fails, you won’t end up in a situation where the message was partially processed.
          - Use **transactions** in the database or external store. First, process the message and store the result **within a single transaction**. If anything goes wrong, the entire transaction is rolled back, ensuring that the message is either fully processed or not processed at all.
          - Only **commit the offset** after the transaction is successfully completed, ensuring that if the consumer fails before the commit, the message will be reprocessed, but idempotently.
      - **Use Kafka Transactions (Exactly-Once Semantics)**
        - If you need **exactly-once semantics (EOS)**, Kafka provides a **transactional API** starting from version 0.11. This allows you to combine **message consumption**, **processing**, and **offset commits** into a single atomic transaction, ensuring that messages are processed **exactly once** without duplicates.

- Partition assignment and rebalance
  - When rebalance happen, it is possible to reprocess message, be careful handling rebalance.




## 5. What will happen if some consumers are down in a consumer group？ Will data loss occur? Why

Data do not loss. 

1. Partition Rebalancing. 
   - Each partition is assigned to a consumer within a **consumer group**. If this consumer down, Kafka will rebalance to redistribute the partitions among the remaining active consumers. The active consumers will consume messages start with the last committed offset.
2. Data is not lost, because messages are persist in Kafka broker's partitions, consumers down will not affect those message.

Of course there is something to consider. 

- Uncommitted offsets
  - If a consumer crashes **before committing its offset** for messages it has already consumed and processed, there is a risk that these messages could be **reprocessed** when the partition is reassigned to another consumer. This is because the new consumer will pick up from the last successfully committed offset, which might be behind the actual progress.
  - In this case, **duplicate consumption** could occur, but not data loss. Making the consumer **idempotent** (able to process the same message multiple times without side effects) can mitigate this issue.
- Delayed processing
  - If there are not enough remaining consumers to handle all the partitions, some partitions may remain **unconsumed** until new consumers join the group or the downed consumers recover. During this time, no messages will be consumed from the unassigned partitions, leading to a delay in processing.
  - However, the data is still safely stored in Kafka and will be processed once consumers are available.

## 6. What will happen if an entire consumer group is down? Will data loss occur? Why?

No data loss under the retention policy duration, or it might has the risk of losing data.

Compare to previous one.

- Messages consumption temporarily stop.
- Delayed processing

Can Handle this with High Availability setup

In a highly available setup, you can deploy multiple consumers in the same consumer group across different machines or regions. This helps ensure that even if one or more consumers fail, others continue processing messages. However, if all consumers in the group are down, message consumption will halt until the group is back online, but data is safe.



## 7. Explain consumer lag and how to solve it.

In short, the time need to process the number of messages that the consumer has not yet processed but are waiting in the partition.

### How Consumer Lag Occurs:

1. **Message Ingestion Rate > Consumer Processing Rate**:
   - If the rate at which producers send messages to a topic is faster than the rate at which consumers can process them, consumer lag will grow. The consumer is not able to keep up with the incoming message stream, causing messages to pile up in the partition.
2. **Slow Consumers**:
   - Consumers might be slow due to factors like inefficient processing logic, limited computing resources, network bottlenecks, or downstream systems (e.g., databases) being too slow to handle the load.
3. **Rebalancing Delays**:
   - If a rebalance occurs (e.g., when a consumer joins or leaves a group), there is a short period where consumers are not processing messages, and this could lead to lag building up temporarily.
4. **Partition Reassignment**:
   - If partitions are reassigned to new consumers after a rebalance, the new consumers need to start from the last committed offset, and if they are not fast enough, lag can increase.

**Solutions**

1. Increase the number of consumers
2. Optimize the consumer processing logic.



## 8. Explain how kafka tracks message delivery?

1. Offset Tracking tracks the position of message in a partition, which is a incremental number. Kafka traks which messages a consumer has read or consumed by using these offsets.

2. How to Track Delivery?

   - The offset can track the last message they have successfully consumed. **Consumer explicitly commit offsets** (manually or automatically) to Kafka representing the "checkpoint" indicating the last processed message.
   -  **Manual Offset Commit**:
     - In manual offset management (`enable.auto.commit=false`), the consumer explicitly commits offsets after successfully processing the message. This gives you more control and reliability, as you only commit offsets once the message has been fully processed. This reduces the risk of message loss or duplication in the event of consumer failure.
     - Kafka provides both synchronous (`commitSync()`) and asynchronous (`commitAsync()`) methods for committing offsets.

3. **How Kafka Ensures Message Delivery Reliability**:

   Kafka provides **at-least-once**, **at-most-once**, and **exactly-once** delivery semantics, depending on how offsets are committed and processed:

   #### 1. **At-Least-Once Delivery**:

   - This is the default behavior in Kafka and is achieved through **manual offset commits**.
   - After a message is consumed and processed, the offset is committed. If a consumer crashes before the offset is committed, when it recovers, it will resume from the last committed offset and may reprocess some messages. This ensures that **no messages are missed**, but duplicates may occur if a message is processed again.

   #### 2. **At-Most-Once Delivery**:

   - This can happen when the consumer commits offsets before processing the message. If the consumer crashes after committing the offset but before processing the message, that message will not be processed again, leading to potential message loss.
   - This approach favors speed and minimal reprocessing over message reliability.

   #### 3. **Exactly-Once Delivery (EOS)**:

   - Kafka, starting from version 0.11, supports **exactly-once semantics (EOS)**, which guarantees that each message is processed **exactly once**, even in the presence of failures.
   - This is done by using Kafka’s **transactional APIs**, where a consumer can read, process, and write back to Kafka (or an external system) within a transaction. If anything goes wrong during processing, the entire transaction (including the offset commit) is rolled back.
   - EOS requires careful configuration, using both **transactional producers** and **idempotent consumers**, which guarantees that messages are neither lost nor processed more than once.

   ### 5. **Tracking Message Delivery with Consumer Groups**:

   - Kafka uses **consumer groups** to manage how partitions are distributed across multiple consumers. Each consumer group maintains its own offsets.
   - Different consumer groups can consume the same topic independently, and each group will maintain its own **committed offsets**. For example, if Consumer Group A is at offset 10 and Consumer Group B is at offset 50, they will be consuming messages at different rates without affecting each other.
   - Kafka ensures that **within a consumer group**, each partition is consumed by only one consumer, preventing duplicate consumption of the same partition by multiple consumers in the same group.

   ### 6. **Handling Failures and Restarts**:

   - **Consumer Failures**: If a consumer crashes or restarts, Kafka will reassign its partitions to other consumers in the same group during a **rebalance**. The new consumer that picks up the partition will start consuming from the last committed offset, ensuring no messages are lost.
   - **Graceful Shutdown**: If a consumer is shut down gracefully, it commits its offsets before exiting, ensuring it can resume from the correct position when it restarts.

   ### 7. **Other Mechanisms for Ensuring Message Delivery**:

   - **Replication**: Kafka replicates messages across multiple brokers. Even if a broker fails, the data is still available from the replicas. This ensures no data loss during broker failures, and consumers can still read the messages.
   - **Acks from Producers**: Producers can configure Kafka to wait for acknowledgments from brokers before considering a message as successfully produced. By setting the acknowledgment level to `acks=all`, Kafka ensures that the message has been written to all in-sync replicas, which provides strong guarantees that the message won’t be lost.

## 9. Compare Kafka vs RabbitMQ,  compare messaging frameworks vs MySql(Why kafka)?

In short, RabbitMQ is for performance in low latency, Kafka is for high availability and scalability.

We want processing capabilities, scalability, and retention features in Kafka because they make data and message processing more flexible and safer

**Kafka** is distributed, log-based streaming platform designed for scalable event processing. 

- Kafka follows a **pull-based** model. 
- Kafka provides at-least-once and exactly-once semantics.
- Kafka is designed for **event streaming** and **log storage**, where messages are stored durably and consumed in log-based sequences. 

**RabbitMQ** is traditional message broker that implements Advanced Queuing Protocol (AMQP), making it suitable for message delivery and routing. 

- Use exchanges (message routers) and queues (message buffers)
- Push-based model
- At-most-once, at-least-once and exactly-once 
- Is designed for message routing and work distribution. Where message deliver to specific consumers
- No long-term persistence of messages for recovery
- After consumers fail, message will be requeued but to the back of queue not the front

**Why** Kafka for MySQL

- High throughput and scalability make it better suited for modern data pipeline that require real-time streaming and large-scale data ingestion into MySQL. While MySQL is not designed to handle large volume data directly from multiple, high-frequency sources. Kafka allows MySQL to operate at its own pace, tasks will only distribute to it when the database is ready to process. **Prevent overwhelm** the database.
- Log-based architecture allow for durable message retention, making it easier to reprocess or replay data before storing into MySQL. RabbitMQ mainly focus on transient massage delivery
- Kafka EOS (exactly-once semantics) provides consistency, minimize risk of duplicates or missing records.
- RabbitMQ is better suited for task queues, real-time messaging, and routing scenarios but lacks the stream **processing capabilities, scalability and retention features** that makes Kafka more ideal for building **event-driven** architectures with MySQL



## 10. On top of https://github.com/CTYue/Sprint-Producer-Consumer

Only one consumer in a group is consuming the messages

![kafka single coonsumer group](D:\Development\chuwa0904\img\kafka single coonsumer group.png)

When adding more consumers, still only one consumer is consuming.

![kafka single consumer group 2](D:\Development\chuwa0904\img\kafka single consumer group 2.png)

When add new consumer groups, partitioner in kafka producer use key value hash to decide the message goes to which partitions. 

![kafka multiple consumer group](D:\Development\chuwa0904\img\kafka multiple consumer group.png)

![kafka key hash partition](D:\Development\chuwa0904\img\kafka key hash partition.png)

Also we can change the partition strategy. We get rotating assignment.

```java
// Round-robin partitioner
configProps.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, RoundRobinPartitioner.class);
```

![Kafka RoundRobin](D:\Development\chuwa0904\img\Kafka RoundRobin.png)

At most once

**Definition**: Messages are delivered at most once. There may be message loss, but no message will be delivered twice.

**Use Case**: Applications where losing messages is acceptable (e.g., logging).

**Configuration**: Set `enable.auto.commit=true` (default), and messages are committed as they are consumed.

```java
spring.kafka.consumer.enable-auto-commit=true
```



**At most once**

Consumer 5 will fail, but it will not block the following messages. We have no idea whether the message is processed or not, because the commit is sent back automatically. Benefit is no messages  will be blocked.

```java
spring.kafka.consumer.enable-auto-commit=true
auto.commit.interval.ms=300
```

![at most one](D:\Development\chuwa0904\img\at most one.png)



**At-least-once**

There is possible of duplicates since Kafka will keep sending messages this time. 

After the failure, restart the program, the not responding message will be sent again. And twice it seems.

```java
spring.kafka.consumer.enable-auto-commit=false
```

![at least once](D:\Development\chuwa0904\img\at least once.png)

**Exactly once**

Kafka Transaction makes sure the exactly once, even when failure occurs. Even when a message is sent multiple times, it will only be processed once. 

Can define in producer whether retry or by default abort the message. 

~~~
# Procuder #
spring.kafka.producer.transaction-id-prefix=tx-
spring.kafka.producer.transaction-id-prefix=my-transaction-id
spring.kafka.producer.acks=all

#Consumer config
spring.kafka.consumer.isolation.level=read_committed
spring.kafka.consumer.enable-auto-commit=false
~~~

~~~java
@Configuration
public class KafkaProducerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    public String bootstrapServers;

    @Bean
    public ProducerFactory<String, String> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, "true");  // Idempotent producer
        configProps.put(ProducerConfig.ACKS_CONFIG, "all");
        configProps.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "tx-producer-1");

        // Round-robin partitioner
        configProps.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, RoundRobinPartitioner.class);

        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
~~~

~~~java
@EnableKafka
@Configuration
public class KafkaConsumerConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    public String bootstrapServers;

    @Value("${spring.kafka.consumer.group-id}")
    public String consumerGroupId;

    @Value("${kafka.topic.name}")
    public String topic;

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapServers);
        props.put(
                ConsumerConfig.GROUP_ID_CONFIG,
                consumerGroupId);
        props.put(
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class);
        props.put(
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String>
    kafkaListenerContainerFactory() {

        ConcurrentKafkaListenerContainerFactory<String, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);
        factory.setConcurrency(3);
        return factory;
    }

}
~~~



~~~java
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
~~~

~~~java
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
~~~



### Summary of Delivery Guarantees and Setup

| Guarantee     | Configuration                                          | Behavior                                 |
| ------------- | ------------------------------------------------------ | ---------------------------------------- |
| At-most-once  | `enable.auto.commit=true`                              | Message might be lost, no duplicates     |
| At-least-once | `enable.auto.commit=false`, manual commit              | No message loss, but duplicates possible |
| Exactly-once  | Transactions enabled, `isolation.level=read_committed` | No message loss, no duplicates           |
