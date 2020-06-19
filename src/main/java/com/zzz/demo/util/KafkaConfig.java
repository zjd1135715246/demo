package com.zzz.demo.util;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangjiadong
 * @date 2020/5/27
 */
@Configuration
@EnableKafka
public class KafkaConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private  String HOST;
    //生产者配置
    @Value("${kafkaProduce.retries}")
    private  Integer retries;
    @Value("${kafkaProduce.batchSize}")
    private  Integer batchSize;
    @Value("${kafkaProduce.lingerMs}")
    private  Integer lingerMs;
    @Value("${kafkaProduce.bufferMemory}")
    private  Integer bufferMemory;
    //消费者配置
    @Value("${kafkaCustom.autoCommitTime}")
    private String autoCommitTime;
    @Value("${kafkaCustom.sessionTimeout}")
    private String sessionTimeout;
    @Value("${kafkaCustom.maxPollRecords}")
    private String maxPollRecords;
    @Value("${kafkaCustom.concurrency}")
    private Integer concurrency;


    //生产者配置
    private Map<String, Object> senderProps (){
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, HOST);
        //重试，0为不启用重试机制
        props.put(ProducerConfig.RETRIES_CONFIG, retries);
        //控制批处理大小，单位为字节
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, batchSize);
        //批量发送，延迟为1毫秒，启用该功能能有效减少生产者发送消息次数，从而提高并发量
        props.put(ProducerConfig.LINGER_MS_CONFIG, lingerMs);
        //生产者可以使用的总内存字节来缓冲等待发送到服务器的记录
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, bufferMemory);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return props;
    }
    //根据senderProps填写的参数创建生产者工厂
    @Bean("producerFactory")
    public ProducerFactory<Object, String> producerFactory() {
        final DefaultKafkaProducerFactory<Object, String> kafkaProducerFactory = new DefaultKafkaProducerFactory<>(senderProps());
        //开启kafka事务
//        kafkaProducerFactory.transactionCapable();
//        //生成Transactional.id的前缀
//        kafkaProducerFactory.setTransactionIdPrefix("tran-");
        return kafkaProducerFactory;
    }
//    @Bean("kafkaTransactionManager")
//    public KafkaTransactionManager transactionManager(ProducerFactory producerFactory) {
//        KafkaTransactionManager manager = new KafkaTransactionManager(producerFactory);
//        return manager;
//    }
    @Bean("kafkaTemplate")
    @Primary
    public KafkaTemplate<Object, String> kafkaTemplate() {
        KafkaTemplate<Object, String> template = new KafkaTemplate(producerFactory());
        return template;
    }
    //kafkaTemplate实现了Kafka发送接收等功能

    //消费者配置
    private Map<String, Object> consumerProps() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, HOST);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, autoCommitTime);
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, sessionTimeout);
        //一次拉取消息数量
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, maxPollRecords);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return props;
    }
    @Bean("batchContainerFactory")
    public ConcurrentKafkaListenerContainerFactory listenerContainer() {
        ConcurrentKafkaListenerContainerFactory container = new ConcurrentKafkaListenerContainerFactory();
        container.setConsumerFactory(new DefaultKafkaConsumerFactory(consumerProps()));
        //设置并发量，小于或等于Topic的分区数
        container.setConcurrency(concurrency);
        //设置为批量监听
        container.setBatchListener(true);
        return container;
    }


    //创建topic
    @Bean
    public KafkaAdmin kafkaAdmin() {
        KafkaAdmin admin = new KafkaAdmin(senderProps());
        return admin;
    }
    @Bean("adminClient")
    public AdminClient adminClient() {
        return AdminClient.create(kafkaAdmin().getConfig());
    }

}
