package com.transactionservices.TransactionComparisonApplication.model;
import com.transactionservices.TransactionComparisonApplication.model.Metadata;
import com.transactionservices.TransactionComparisonApplication.model.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TransactionTest {

    @Test
    public void testTransaction() {
        Metadata metadata = new Metadata(1, 2);
        Transaction transaction = new Transaction(100.5, metadata);
        assertEquals(100.5, transaction.getAmount());
        assertEquals(metadata, transaction.getMetadata());
    }
}
