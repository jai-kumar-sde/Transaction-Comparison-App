package com.transactionservices.TransactionComparisonApplication.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.transactionservices.TransactionComparisonApplication.model.ResultMessage;
@Service
public class PublishResultService {

    private final Logger logger = LoggerFactory.getLogger(TransactionKafkaConsumerService.class);

	@Autowired
    private SimpMessagingTemplate template;

    /**
     * Publishes the result to the specified topic.
     * 
     * @param result The result to be published
     */
    public void publishResult(ResultMessage result) {
        try {
            System.out.println("Sending via Kafka listener: " + result);
            
           template.convertAndSend("/topic/pushResult", result);
          
           logger.info("Result published successfully.");
 
        } catch (Exception e) {
            // Logging the exception occurred while publishing the result
            logger.error("Task status is updated: " + e.getMessage());
        }
    }
}
