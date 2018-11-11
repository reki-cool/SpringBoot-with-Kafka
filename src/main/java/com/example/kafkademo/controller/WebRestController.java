package com.example.kafkademo.controller;

import com.example.kafkademo.services.KafkaProducer;
import com.example.kafkademo.storage.MessageStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/kafka-demo")
public class WebRestController {

    @Autowired
    KafkaProducer producer;

    @Autowired
    MessageStorage storage;

    @GetMapping(value="/producer")
    public String producer(@RequestParam("data")String data){
        producer.send(data);

        return "Done";
    }

    @GetMapping(value="/consumer")
    public String getAllRecievedMessage(){
        String messages = storage.toString();
        storage.clear();

        return messages;
    }
}
