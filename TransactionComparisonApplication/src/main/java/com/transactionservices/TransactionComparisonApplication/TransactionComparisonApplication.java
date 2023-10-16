package com.transactionservices.TransactionComparisonApplication;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.transactionservices.TransactionComparisonApplication.service.TransactionKafkaConsumerService;

@SpringBootApplication
@ComponentScan
public class TransactionComparisonApplication {
    private static final Logger logger = LoggerFactory.getLogger(TransactionComparisonApplication.class);

    public static void main(String[] args) {
        try {
            SpringApplication.run(TransactionComparisonApplication.class, args);
        } catch (Exception e) {
            // Handle any exception that occurred during application startup
            logger.error("An error occurred during application startup", e);
        }
    }
}



