package com.example.ktb.kafka;

import com.example.ktb.kafka.message.Message;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
public class KafkaConfig {

    private final KafkaProperties kafkaProperties;

    public KafkaConfig(KafkaProperties kafkaProperties) {
        this.kafkaProperties = kafkaProperties;
    }

    @Bean
    ConcurrentKafkaListenerContainerFactory<String, Message>
    kafkaListenerContainerFactory(ConsumerFactory<String, Message> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, Message> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.BATCH);
        factory.setConcurrency(kafkaProperties.getListener().getConcurrency());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, Message> kafkaConsumer() {
        return new DefaultKafkaConsumerFactory<>(kafkaProperties.getConsumer().buildProperties(), new StringDeserializer(), new JsonDeserializer<>(Message.class, false));
    }

    @Bean
    public ProducerFactory<String, Message> greetingProducerFactory() {
        return new DefaultKafkaProducerFactory<>(kafkaProperties.getProducer().buildProperties(), new StringSerializer(), new JsonSerializer<>());
    }

    @Bean
    public KafkaTemplate<String, Message> greetingKafkaTemplate(ProducerFactory<String, Message> greetingProducerFactory) {
        return new KafkaTemplate<>(greetingProducerFactory);
    }
}
