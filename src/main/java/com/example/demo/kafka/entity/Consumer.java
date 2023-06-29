package com.example.demo.kafka.entity;

import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
    public String[] Topics;
    public Consumer(String[] topics)
    {
        Topics=topics;
    }
    /*@KafkaListener(topics = Topics)
    public void listen(ConsumerRecord<?,?> record){

    }*/
}
