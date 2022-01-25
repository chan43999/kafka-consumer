package com.example.ktb.kafka;

import com.example.ktb.kafka.message.Friend;
import com.example.ktb.kafka.message.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@Slf4j
public class PublishController {

    @Value("${spring.kafka.producer.topic}")
    private String topic;

    private final KafkaTemplate<String, Message> sender;

    public PublishController(KafkaTemplate<String, Message> sender) {
        this.sender = sender;
    }

    @GetMapping("/{amount}")
    public void publish(@PathVariable Integer amount) {

        Message name = Message.builder()
                ._id("213132")
                .friends(Collections.singletonList(new Friend(123, "name")))
                .build();
        for (int i = 0; i < amount; i++) {

            sender.send(topic, name).addCallback(new ListenableFutureCallback<SendResult<String, Message>>() {
                @Override
                public void onFailure(Throwable ex) {

                }

                @Override
                public void onSuccess(SendResult<String, Message> result) {
                    log.info("finish {}", result.getRecordMetadata().offset());
                }
            });
        }
    }
}
