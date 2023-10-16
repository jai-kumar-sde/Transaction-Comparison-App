package com.transactionservices.TransactionComparisonApplication.service;
import com.transactionservices.TransactionComparisonApplication.model.ResultMessage;
import com.transactionservices.TransactionComparisonApplication.model.TransactionMessage;
import com.transactionservices.TransactionComparisonApplication.service.CommonComparisonService;
import com.transactionservices.TransactionComparisonApplication.service.PublishResultService;
import com.transactionservices.TransactionComparisonApplication.service.TransactionKafkaConsumerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class TransactionKafkaConsumerServiceTest {

    @Mock
    private CommonComparisonService commonComparisonService;

    @Mock
    private PublishResultService publishResultService;

    @Mock
    private Logger logger;

    @InjectMocks
    private TransactionKafkaConsumerService transactionKafkaConsumerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testConsume() {
        String kafkaTopicJson = "[{\"PID\": 123, \"PAMOUNT\": 100.5, \"PDATA\": 20230101120000}]";

        List<TransactionMessage> transactionMessages = new ArrayList<>();
        TransactionMessage transactionMessage = new TransactionMessage(123, 100.5, 20230101120000L);
        transactionMessages.add(transactionMessage);

        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setComparisonStatus("✅ Success");
        resultMessage.setTransactionId(123);
        resultMessage.setOriginalAmount(100.5);
        resultMessage.setReceivedAmount(100.5);

        // Mocking the behavior of commonComparisonService
        Mockito.when(commonComparisonService.compareTransaction(transactionMessage)).thenReturn(resultMessage);

        transactionKafkaConsumerService.consume(kafkaTopicJson);

    }
}
