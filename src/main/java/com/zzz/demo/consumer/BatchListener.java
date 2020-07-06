package com.zzz.demo.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author zhangjiadong
 * @date 2020/5/27
 */
//@Component
public class BatchListener {

    private static final Logger log= LoggerFactory.getLogger(BatchListener.class);

//    @Bean
//    public NewTopic batchTopic() {
//        return new NewTopic("topic.quick.batch", 8, (short) 1);
//    }
    @KafkaListener(id = "batch",clientIdPrefix = "batch",topics = {"#{'${kafkaCustom.kafkaBatchListenerList}'.split(',')}"},containerFactory = "batchContainerFactory")
    public void batchListener(List<ConsumerRecord<?, ?>> records) {
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        for (ConsumerRecord<?, ?> record : records) {
            Optional<?> kafkaMessage = Optional.ofNullable(record.value());
            if (kafkaMessage.isPresent()) {
                String message = (String) kafkaMessage.get();
                String topic = record.topic();
                switch (topic){
                    case "testBatch1":{
                        list1.add(message);
                        break;
                    }
                    case "testBatch2":{
                        list2.add(message);
                        break;
                    }
                    default:{
                        System.out.println("ni xx");
                    }
                }
            }
        }
        list1.forEach(System.out::println);
    }

}
