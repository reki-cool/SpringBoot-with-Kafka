# Spring Boot 整合 Kafka示例
## 示例简介
使用SpringBoot, Kafka组件，构建一个基本的消息发送和接收的示例
> - Kafka的安装使用：https://grokonez.com/java-integration/start-apache-kafka
> - SpringBoot整合Kafka：https://grokonez.com/java-integration/distributed-system/start-spring-apache-kafka-application-springboot
## 示例图形化结构
![struction](https://raw.githubusercontent.com/duyanhan1995/SpringBoot-with-Kafka/master/src/main/resources/SpringBoot-Kafka-Application.png)
## 构建步骤
### 创建应用程序
 - idea -> Create New Project -> Spring Initializr
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
### 部署kafka （[参考](https://grokonez.com/java-integration/start-apache-kafka)）
 - 启动zookeeper，因为Apache Kafka需要使用ZooKeeper作为集中服务
    ```bash
    H:\kafka_2.12-2.0.1\bin\windows>zookeeper-server-start.bat ..\..\config\zookeeper.properties
    ```
 - 启动kafka
    ```bash
    H:\kafka_2.12-2.0.1\bin\windows>kafka-server-start.bat ..\..\config\server.properties
    ```
### 构建&&运行
在idea的Terminal控制台输入以下命令
```bash
mvn clean install && mvn spring-boot:run
```

### 测试
 - 浏览器访问：```http://localhost:8080/kafka-demo/producer?data=Hello World```
    控制台日志结果：
    ```bash
    ...
    2017-06-08 13:49:47.111  INFO 12240 --- [io-8080-exec-10] c.j.apachekafka.services.KafkaProducer   : sending data='Hello World'
    ...
    2017-06-08 13:49:47.248  INFO 12240 --- [ntainer#0-0-L-1] c.j.apachekafka.services.KafkaProducer   : received content = 'Hello World'
    ``` 
 - 浏览器再访问：```http://localhost:8080/kafka-demo/producer?data=This is a SpringBoot Kafka Application```
   控制台日志结果：
    ```bash
   2017-06-08 13:51:34.909  INFO 12240 --- [nio-8080-exec-7] c.j.apachekafka.services.KafkaProducer   : sending data='This is a SpringBoot
   Kafka Application'
   2017-06-08 13:51:34.913  INFO 12240 --- [ntainer#0-0-L-1] c.j.apachekafka.services.KafkaProducer   : received content = 'This is a Sprin
   gBoot Kafka Application'
    ```
 - 浏览器再访问：```http://localhost:8080/kafka-demo/consumer```
    浏览器结果：
   ![结果](https://raw.githubusercontent.com/duyanhan1995/SpringBoot-with-Kafka/master/src/main/resources/webResult.png)
    