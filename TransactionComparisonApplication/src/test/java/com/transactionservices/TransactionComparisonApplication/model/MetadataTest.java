package com.transactionservices.TransactionComparisonApplication.model;
import com.transactionservices.TransactionComparisonApplication.model.Metadata;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MetadataTest {

    @Test
    public void testMetadata() {
        Metadata metadata = new Metadata(1, 2);
        assertEquals(1, metadata.getOriginatorId());
        assertEquals(2, metadata.getDestinationId());
    }
}
