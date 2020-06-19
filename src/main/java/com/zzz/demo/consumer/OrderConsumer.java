package com.zzz.demo.consumer;

import com.alibaba.fastjson.JSON;
import com.zzz.demo.entity.Address;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * OrderConsumer class
 *
 * @author chenlingyu
 * @date 2020/5/14 18:59
 */
@Component
@Slf4j
public class OrderConsumer {

    @KafkaListener(topics = "#{'${kafkaCustom.kafkaSingleListenerList}'.split(',')}")
    public void orderListener(ConsumerRecord<?, ?> record) {
        String topic = record.topic();
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            String message = (String) kafkaMessage.get();
            switch (topic){
                case "UserAddress":{
                    JSON.parseObject(message, Address.class);
                    break;
                }
                case "CreateOrder":{
                    break;
                }
                case "ReportOrder":{
                    break;
                }
                default:{
                    System.out.println("异常");
                }
            }

        }
    }
}
