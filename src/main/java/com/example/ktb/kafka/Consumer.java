package com.example.ktb.kafka;

import com.example.ktb.kafka.message.Message;
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

    @KafkaListener(topics = "${spring.kafka.consumer." +
            "topics}", containerFactory = "kafkaListenerContainerFactory")
    public void listen(@Payload Message message, @Header(KafkaHeaders.OFFSET) List<Long> offsets) throws InterruptedException {
        TimeUnit.MICROSECONDS.sleep(5);
        log.info("perf {}", message);
    }
}
