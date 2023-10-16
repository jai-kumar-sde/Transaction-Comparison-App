package com.transactionservices.TransactionComparisonApplication.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

@SpringBootTest
@AutoConfigureMockMvc
public class TransactionKafkaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private KafkaTemplate<String, String> kafkaTemplate;

    @Test
    public void testSendEvents() throws Exception {
        // Mocking Kafka data
      //  doNothing().when(kafkaTemplate).send(any(), any(), any());

        String kafkaTopic = "{\"PID\": 123, \"PAMOUNT\": 94.7, \"PDATA\": 20160101120000 }";
        mockMvc.perform(MockMvcRequestBuilders.post("/kafka/publish")
                .contentType("application/json")
                .content(kafkaTopic))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
