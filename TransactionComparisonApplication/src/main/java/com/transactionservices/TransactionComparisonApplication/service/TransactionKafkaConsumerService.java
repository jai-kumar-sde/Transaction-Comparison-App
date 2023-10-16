package com.transactionservices.TransactionComparisonApplication.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.transactionservices.TransactionComparisonApplication.model.ResultMessage;
import com.transactionservices.TransactionComparisonApplication.model.TransactionMessage;
@Service
public class TransactionKafkaConsumerService {

    private final Logger logger = LoggerFactory.getLogger(TransactionKafkaConsumerService.class);

    @Autowired
    private CommonComparisonService commonComparisonService;

    @Autowired
    private PublishResultService publishResultService;

    @KafkaListener(topics = {"general-task-topic"}, groupId = "task-group")
    public void consume(String taskStatus) {
        logger.info("Task status is updated: " + taskStatus);
        getListOfTransactionMessages(taskStatus);
    }

    private List<TransactionMessage> getListOfTransactionMessages(String kafkaTopicJson) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<TransactionMessage> transactionMessages = null;

        try {
            // Convert the JSON string to a list of TransactionMessage objects
            transactionMessages = objectMapper.readValue(kafkaTopicJson, new TypeReference<List<TransactionMessage>>() {});
            

            // Process each TransactionMessage
            for (TransactionMessage transactionMessage : transactionMessages) {
                ResultMessage result = commonComparisonService.compareTransaction(transactionMessage);
                publishResultService.publishResult(result);
            }
        } catch (JsonMappingException e) {
            // Log the exception
            logger.error("Error occurred during JSON mapping", e);
        } catch (JsonProcessingException e) {
            // Log the exception
            logger.error("Error occurred during JSON processing", e);
        }

        return transactionMessages;
    }
}
