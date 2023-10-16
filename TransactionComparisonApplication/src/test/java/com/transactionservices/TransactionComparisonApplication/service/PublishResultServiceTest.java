package com.transactionservices.TransactionComparisonApplication.service;
import com.transactionservices.TransactionComparisonApplication.model.ResultMessage;
import com.transactionservices.TransactionComparisonApplication.service.PublishResultService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@SpringBootTest
public class PublishResultServiceTest {

    @Mock
    private SimpMessagingTemplate template;

    @Mock
    private Logger logger;

    @InjectMocks
    private PublishResultService publishResultService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testPublishResult() {
        ResultMessage resultMessage = new ResultMessage();
        resultMessage.setComparisonStatus("✅ Success");
        resultMessage.setTransactionId(123);
        resultMessage.setOriginalAmount(100.5);
        resultMessage.setReceivedAmount(100.5);

        publishResultService.publishResult(resultMessage);

        // Verifying if the template's convertAndSend method is invoked with the expected parameters
        Mockito.verify(template, Mockito.times(1)).convertAndSend("/topic/pushResult", resultMessage);
    }
}
