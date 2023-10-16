package com.transactionservices.TransactionComparisonApplication.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TransactionKafkaProducerService {
	private final Logger LOGGER = LoggerFactory.getLogger(TransactionKafkaProducerService.class);
	
	  @Autowired
	  KafkaTemplate<String, String> kafkaTemplate;

	  public void send(String topicName, String key, String value) {
	try {
		LOGGER.info("Sending transaction message to Kafka topic: " + topicName);

	    var future = kafkaTemplate.send(topicName, key, value);

	    future.whenComplete((sendResult, exception) -> {
	      if (exception != null) {
	        future.completeExceptionally(exception);
	      } else {
	        future.complete(sendResult);
	      }
	      LOGGER.info("Transaction send to Kafka topic : "+ value);
	    });
	} catch (Exception e) {
		LOGGER.error("An error occurred while sending message to Kafka topic", e);
	}
	  }
}
