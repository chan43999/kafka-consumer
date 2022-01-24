package com.example.ktb.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class Consumer {

    private long start = 0;


    @KafkaListener(topics = "up5", containerFactory = "kafkaListenerContainerFactory")
    public void listen(@Payload MyMessage message,@Header(KafkaHeaders.OFFSET) List<Long> offsets) throws InterruptedException {
        if (start == 0) {
            start = System.currentTimeMillis();

        }
        TimeUnit.MICROSECONDS.sleep(5);
        log.info("perf {}", System.currentTimeMillis()- start);

    }
}
