package com.example.ktb.kafka;

import com.example.ktb.kafka.message.Friend;
import com.example.ktb.kafka.message.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
@RestController
public class PublishController {

    @Value("${spring.kafka.producer.topic}")
    private String topic;

    private final KafkaTemplate<String, Message> sender;

    public PublishController(KafkaTemplate<String, Message> sender) {
        this.sender = sender;
    }

    @GetMapping("/{amount}")
    public void publish(@PathVariable Integer amount) {

        for (int i = 0; i < amount ; i ++) {
            sender.send(topic, Message.builder()
                    ._id("213132")
                    .friends(Collections.singletonList(new Friend(123, "name")))
                    .build());
        }
    }
}
