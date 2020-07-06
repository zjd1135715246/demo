package com.zzz.demo.util;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.stereotype.Component;

/**
 * KafkaSendResultHandler class
 *
 * @author zzz
 * @date 2020/5/14 19:14
 */
//@Component
public class KafkaSendResultHandler implements ProducerListener {

    @Override
    public void onSuccess(ProducerRecord producerRecord, RecordMetadata recordMetadata) {

    }

    @Override
    public void onError(ProducerRecord producerRecord, Exception exception) {
    }
}