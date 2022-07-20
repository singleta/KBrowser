package com.example.kbrowser.controller;

import com.example.kbrowser.api.KBrowserApi;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Duration;
import java.util.Collections;
import java.util.Map;

public class KController implements KBrowserApi {

    @Autowired
    KafkaConsumer<String, String> consumer;

    @Override
    public ResponseEntity<String> readTopic(Map<String, String> params, Map<String, String> headers) {
        String messages = "";
        messages = getKafkaTopicMessages(params);
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    public String getKafkaTopicMessages(Map<String, String> params) {
        String topic = params.get("topic");

        consumer.subscribe(Collections.singletonList(topic));
        ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
        StringBuilder sb = new StringBuilder();
        records.forEach(cr -> {
            sb.append("key = ").append(cr.key()).append(" value = ").append(cr.value()).append("\n");
        });

        return sb.toString();
    }
}
