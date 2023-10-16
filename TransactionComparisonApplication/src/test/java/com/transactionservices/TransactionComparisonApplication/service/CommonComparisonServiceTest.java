package com.transactionservices.TransactionComparisonApplication.service;
import com.transactionservices.TransactionComparisonApplication.Configuration.WebSocketConfig;
import com.transactionservices.TransactionComparisonApplication.model.ResultMessage;
import com.transactionservices.TransactionComparisonApplication.model.Transaction;
import com.transactionservices.TransactionComparisonApplication.model.TransactionMessage;
import com.transactionservices.TransactionComparisonApplication.service.CommonComparisonService;
import com.transactionservices.TransactionComparisonApplication.service.TransactionRedisService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CommonComparisonServiceTest {

    @Mock
    private TransactionRedisService transactionRedisService;

    @Mock
    private Logger logger;

    @InjectMocks
    private CommonComparisonService commonComparisonService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCompareTransaction() {
        TransactionMessage transactionMessage = new TransactionMessage(123, 100.5, 20230101120000L);

        // Mocking the behavior of transactionRedisService
        Mockito.when(transactionRedisService.getData("123")).thenReturn("{\"amount\":100.5,\"metadata\":{\"originatorId\":1,\"destinationId\":2}}");

        ResultMessage resultMessage = commonComparisonService.compareTransaction(transactionMessage);
        assertEquals(100.5, resultMessage.getOriginalAmount());
        assertEquals(100.5, resultMessage.getReceivedAmount());
        assertEquals("✅ Amounts are equal", resultMessage.getComparisonStatus());
    }
}
