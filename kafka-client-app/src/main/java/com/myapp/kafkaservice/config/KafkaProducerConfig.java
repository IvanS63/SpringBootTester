package com.myapp.kafkaservice.config;

import com.myapp.kafkaservice.util.CustomRequestSerializer;
import com.myapp.kafkaservice.dto.SendRequestDto;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * KafkaProducerConfig.
 *
 * @author Ivan_Semenov
 */
@Configuration
public class KafkaProducerConfig {

    @Value("${kafka.bootstrap-servers}")
    private String servers;

    @Bean
    public ProducerFactory<String, SendRequestDto> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, servers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, CustomRequestSerializer.class);
        configProps.put(ProducerConfig.MAX_BLOCK_MS_CONFIG, "60000");//message timeout
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, servers);
        return new KafkaAdmin(configs);
    }

    @Bean
    public KafkaTemplate<String, SendRequestDto> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
