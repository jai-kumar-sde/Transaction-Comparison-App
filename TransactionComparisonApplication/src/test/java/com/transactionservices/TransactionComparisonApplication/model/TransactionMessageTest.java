package com.transactionservices.TransactionComparisonApplication.model;
import com.transactionservices.TransactionComparisonApplication.model.TransactionMessage;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class TransactionMessageTest {

    @Test
    public void testTransactionMessage() {
        TransactionMessage transactionMessage = new TransactionMessage(123, 100.5, 20230101120000L);
        assertEquals(123, transactionMessage.getPid());
        assertEquals(100.5, transactionMessage.getpAmount());
        assertEquals(20230101120000L, transactionMessage.getpData());
    }

    @Test
    public void testDefaultConstructor() {
        TransactionMessage transactionMessage = new TransactionMessage();
        assertNull(transactionMessage.getPid());
        assertNull(transactionMessage.getpAmount());
        assertNull(transactionMessage.getpData());
    }
}
