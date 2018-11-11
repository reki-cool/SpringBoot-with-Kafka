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
        - WebRestController
    - services // 业务
        - KafkaConsumer
        - KafkaProducer
    - storage  // 存储类包
        - MessageStorage
        
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