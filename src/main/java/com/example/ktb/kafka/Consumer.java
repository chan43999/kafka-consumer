package com.example.ktb.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @KafkaListener(topics = "test", containerFactory = "kafkaListenerContainerFactory")
    public void listen(@Payload MyMessage message, Acknowledgment acknowledgment){
        System.out.println(message);
        acknowledgment.acknowledge();
    }
}
