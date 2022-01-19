package com.example.ktb.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Publish {

    @Autowired
    public KafkaTemplate<String, MyMessage> sender;
    @GetMapping
    public void publish(){
        sender.send("test",new MyMessage("chan",12));
    }
}
