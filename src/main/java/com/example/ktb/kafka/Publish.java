package com.example.ktb.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Publish {

    private final KafkaTemplate<String, MyMessage> sender;

    public Publish(KafkaTemplate<String, MyMessage> sender) {
        this.sender = sender;
    }

    @GetMapping
    public void publish() {
        sender.send("test", new MyMessage("chan", 12));
    }
}
