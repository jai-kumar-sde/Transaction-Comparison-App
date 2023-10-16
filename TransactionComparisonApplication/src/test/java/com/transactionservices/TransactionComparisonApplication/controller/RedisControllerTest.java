package com.transactionservices.TransactionComparisonApplication.controller;

import com.transactionservices.TransactionComparisonApplication.service.TransactionRedisService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class RedisControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TransactionRedisService redisService;

    @Test
    public void testCreateData() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/data/testKey")
                .contentType(MediaType.APPLICATION_JSON)
                .content("testValue"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Data created successfully"));
    }

    @Test
    public void testGetData() throws Exception {
        redisService.createData("testKey", "testValue");
        mockMvc.perform(MockMvcRequestBuilders.get("/data/testKey"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("testValue"));
    }

    @Test
    public void testUpdateData() throws Exception {
        redisService.createData("testKey", "testValue");
        mockMvc.perform(MockMvcRequestBuilders.put("/data/testKey")
                .contentType(MediaType.APPLICATION_JSON)
                .content("updatedValue"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Data updated successfully"));
    }

    @Test
    public void testDeleteData() throws Exception {
        redisService.createData("testKey", "testValue");
        mockMvc.perform(MockMvcRequestBuilders.delete("/data/testKey"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Data deleted successfully"));
    }
}
