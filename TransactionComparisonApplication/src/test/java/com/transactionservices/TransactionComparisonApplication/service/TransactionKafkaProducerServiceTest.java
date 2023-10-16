package com.transactionservices.TransactionComparisonApplication.service;
import com.transactionservices.TransactionComparisonApplication.service.TransactionKafkaProducerService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootTest
public class TransactionKafkaProducerServiceTest {

    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;

    @Mock
    private Logger logger;

    @InjectMocks
    private TransactionKafkaProducerService transactionKafkaProducerService;

    @Test
    public void testSend() {
        String topicName = "test-topic";
        String key = "test-key";
        String value = "test-value";

        // Mocking the behavior of kafkaTemplate
        Mockito.when(kafkaTemplate.send(topicName, key, value)).thenReturn(null);

        transactionKafkaProducerService.send(topicName, key, value);
    }

    @Test
    public void testSendWithException() {
        String topicName = "test-topic";
        String key = "test-key";
        String value = "test-value";

        // Mocking the behavior of kafkaTemplate with an exception
        Mockito.when(kafkaTemplate.send(topicName, key, value)).thenThrow(new RuntimeException("Kafka Exception"));

        transactionKafkaProducerService.send(topicName, key, value);

    }
}
