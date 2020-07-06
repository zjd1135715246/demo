package com.zzz.demo.util;

import com.alibaba.fastjson.JSON;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * KafkaHelper class
 *
 * @author chenlingyu
 * @date 2020/5/14 19:05
 */
//@Component
public class KafkaHelper {

    @Autowired
    private KafkaTemplate<Object,String> kafkaTemplate;

    @Autowired
    private KafkaSendResultHandler producerListener;

    @Autowired
    private AdminClient adminClient;


    public void send(String topic,Object message){
        kafkaTemplate.setProducerListener(producerListener);
        kafkaTemplate.send(topic, JSON.toJSONString(message));
    }

    /**
     * 创建topic
     * @param name          名称
     * @param numPartitions 分区数
     */
    public void createTopic(String name,Integer numPartitions){
        NewTopic topic = new NewTopic(name, numPartitions, (short) 1);
        adminClient.createTopics(Arrays.asList(topic));
    }
}
