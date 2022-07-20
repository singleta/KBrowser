package com.example.kbrowser.configuration;

import static org.apache.kafka.clients.consumer.ConsumerConfig.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@EnableKafka
@Configuration
public class ConsumerConfiguration {

    @Value("${spring.kafka.consumer.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    @Value("${spring.kafka.consumer.auto-offset-reset}")
    private String autoOffsetReset;

    @Value("${spring.kafka.consumer.key-deserializer}")
    private String keyDeserializer;

    @Value("${spring.kafka.consumer.value-deserializer}")
    private String valueDeserializer;

    @Value("${spring.kafka.consumer.heartbeat-interval}")
    private String heartbeatInterval;

    @Value("${spring.kafka.consumer.request.timeout.ms}")
    private String requestTimeout;

    @Value("${spring.kafka.consumer.session.timeout.ms}")
    private String sessionTimeout;

    @Value("${spring.kafka.consumer.max.poll.records}")
    private String maxPollRecords;

    @Value("${spring.kafka.consumer.max.poll.interval.ms}")
    private String maxPollInterval;

    @Bean
    public Map<String, Object> consumerConfig() {
        Map<String, Object> consumerProperties = new ConcurrentHashMap<>();
        consumerProperties.put(BOOTSTRAP_SERVERS_CONFIG, getBootstrapServers());
        consumerProperties.put(KEY_DESERIALIZER_CLASS_CONFIG, getKeyDeserializer());
        consumerProperties.put(VALUE_DESERIALIZER_CLASS_CONFIG, getValueDeserializer());
        consumerProperties.put(GROUP_ID_CONFIG, getGroupId());
        consumerProperties.put(AUTO_OFFSET_RESET_CONFIG, getAutoOffsetReset());
        consumerProperties.put(REQUEST_TIMEOUT_MS_CONFIG, getRequestTimeout());
        consumerProperties.put(SESSION_TIMEOUT_MS_CONFIG, getSessionTimeout());
        consumerProperties.put(HEARTBEAT_INTERVAL_MS_CONFIG, getHeartbeatInterval());
        consumerProperties.put(MAX_POLL_RECORDS_CONFIG, getMaxPollRecords());
        consumerProperties.put(MAX_POLL_INTERVAL_MS_CONFIG, getMaxPollInterval());

        return consumerProperties;

    }


    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfig());
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory () {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    public String getBootstrapServers() {
        return bootstrapServers;
    }

    public void setBootstrapServers(String bootstrapServers) {
        this.bootstrapServers = bootstrapServers;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getAutoOffsetReset() {
        return autoOffsetReset;
    }

    public void setAutoOffsetReset(String autoOffsetReset) {
        this.autoOffsetReset = autoOffsetReset;
    }

    public String getKeyDeserializer() {
        return keyDeserializer;
    }

    public void setKeyDeserializer(String keyDeserializer) {
        this.keyDeserializer = keyDeserializer;
    }

    public String getValueDeserializer() {
        return valueDeserializer;
    }

    public void setValueDeserializer(String valueDeserializer) {
        this.valueDeserializer = valueDeserializer;
    }

    public String getHeartbeatInterval() {
        return heartbeatInterval;
    }

    public void setHeartbeatInterval(String heartbeatInterval) {
        this.heartbeatInterval = heartbeatInterval;
    }

    public String getRequestTimeout() {
        return requestTimeout;
    }

    public void setRequestTimeout(String requestTimeout) {
        this.requestTimeout = requestTimeout;
    }

    public String getSessionTimeout() {
        return sessionTimeout;
    }

    public void setSessionTimeout(String sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
    }

    public String getMaxPollRecords() {
        return maxPollRecords;
    }

    public void setMaxPollRecords(String maxPollRecords) {
        this.maxPollRecords = maxPollRecords;
    }

    public String getMaxPollInterval() {
        return maxPollInterval;
    }

    public void setMaxPollInterval(String maxPollInterval) {
        this.maxPollInterval = maxPollInterval;
    }
}
