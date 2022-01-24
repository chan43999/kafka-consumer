package com.example.ktb.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublishController {

    @Value("${spring.kafka.producer.topic}")
    private String topic;

    private final KafkaTemplate<String, MyMessage> sender;

    public PublishController(KafkaTemplate<String, MyMessage> sender) {
        this.sender = sender;
    }

    @GetMapping("/{amount}")
    public void publish(@PathVariable Integer amount) {

        for (int i = 0; i < amount ; i ++) {
            sender.send(topic, new MyMessage("chan" + i, 12));
        }
    }
}
