package com.transactionservices.TransactionComparisonApplication.model;
import com.transactionservices.TransactionComparisonApplication.model.ResultMessage;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class ResultMessageTest {

    @Test
    public void testResultMessage() {
        ResultMessage resultMessage = new ResultMessage();
        assertNull(resultMessage.getTransactionId());
        assertNull(resultMessage.getComparisonStatus());
        assertNull(resultMessage.getOriginalAmount());
        assertNull(resultMessage.getReceivedAmount());
    }
}
