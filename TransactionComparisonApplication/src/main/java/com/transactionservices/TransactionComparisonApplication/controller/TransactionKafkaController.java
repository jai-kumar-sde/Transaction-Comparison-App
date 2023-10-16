package com.transactionservices.TransactionComparisonApplication.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/kafka")
public class TransactionKafkaController {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public TransactionKafkaController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * Publishes a customer event to Kafka topic.
     *
     * @param customer the JSON representation of the customer
     */
    @PostMapping("/publish")
    public void sendEvents(@RequestBody String customer) {
        try {
            kafkaTemplate.send("general-task-topic", "taskId", customer);
            // Log success or any additional information if needed
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
