# Spring Boot 整合 Kafka示例
## 示例简介
使用SpringBoot, Kafka组件，构建一个基本的消息发送和接收的示例
> - Kafka的安装使用：https://grokonez.com/java-integration/start-apache-kafka
> - SpringBoot整合Kafka：https://grokonez.com/java-integration/distributed-system/start-spring-apache-kafka-application-springboot
## 构建步骤
### 创建应用程序
 - Create New Project -> Spring Initializr
 - 选择 http://start.spring.io -> Next
 - Artifact 字段填"kafka-demo"
 - 依赖组件选择：Web, Kafka
 
### 目录结构预览
 - com.example.kafkademo
    - controller // 控制器
        - [WebRestController](https://github.com/duyanhan1995/SpringBoot-with-Kafka/blob/master/src/main/java/com/example/kafkademo/controller/WebRestController.java) // 控制器接口类->包含生产和消费消息的两个接口
    - services // 业务
        - [KafkaConsumer](https://github.com/duyanhan1995/SpringBoot-with-Kafka/blob/master/src/main/java/com/example/kafkademo/services/KafkaConsumer.java) // kafka 消费者->消费(接收处理来自kafka的)消息
        - [KafkaProducer](https://github.com/duyanhan1995/SpringBoot-with-Kafka/blob/master/src/main/java/com/example/kafkademo/services/KafkaProducer.java) // kafka 生产者->生产(产生并发送给kafka)消息
    - storage  // 存储类包
        - [MessageStorage](https://github.com/duyanhan1995/SpringBoot-with-Kafka/blob/master/src/main/java/com/example/kafkademo/storage/MessageStorage.java) // 消息存储类->用来保存生产者产生的消息
        
### 写配置文件
```properties
## 自定义的属性  定义kafka的主题
kafka-demo.kafka.topic=kafka-test
## bootstrap-servers  定义kafka集群的地址和端口
spring.kafka.bootstrap-servers=localhost:9092
## kafka生产者的键值序列化方式
spring.kafka.producer.key-serializer=org.apache.kafka.common.serialization.StringSerializer
spring.kafka.producer.value-serializer=org.apache.kafka.common.serialization.StringSerializer

## consumer.group-id  定义消费者的分组id
spring.kafka.consumer.group-id=test-group
## kafka 消费者的earliest配置消费当前分组下的所有内容
spring.kafka.consumer.auto-offset-reset=earliest
```

