package com.transactionservices.TransactionComparisonApplication.service;
import com.transactionservices.TransactionComparisonApplication.service.TransactionRedisService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class TransactionRedisServiceTest {

    @Mock
    private RedisTemplate<String, Object> redisTemplate;

    @Mock
    private Logger logger;

    @InjectMocks
    private TransactionRedisService transactionRedisService;

    @Test
    public void testCreateData() {
        String key = "124";
        String value = "{\"amount\":150.75,\"metadata\":{\"originatorId\":10,\"destinationId\":20}}";

        transactionRedisService.createData(key, value);

    }

    @Test
    public void testUpdateData() {
        String key = "124";
        String newValue = "{\"amount\":150.75,\"metadata\":{\"originatorId\":10,\"destinationId\":20}}";

        // Mocking the behavior of redisTemplate
        Mockito.when(redisTemplate.hasKey(key)).thenReturn(true);

        transactionRedisService.updateData(key, newValue);

    }

}
