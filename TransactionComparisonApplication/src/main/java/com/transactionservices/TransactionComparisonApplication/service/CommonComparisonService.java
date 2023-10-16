package com.transactionservices.TransactionComparisonApplication.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.transactionservices.TransactionComparisonApplication.Configuration.WebSocketConfig;
import com.transactionservices.TransactionComparisonApplication.model.ResultMessage;
import com.transactionservices.TransactionComparisonApplication.model.Transaction;
import com.transactionservices.TransactionComparisonApplication.model.TransactionMessage;
@Service
public class CommonComparisonService {

	private static final Logger logger = LoggerFactory.getLogger(WebSocketConfig.class);
	
    @Autowired
    TransactionRedisService transactionRedisService;

    public ResultMessage compareTransaction(TransactionMessage message) {
        ResultMessage result=new ResultMessage();
        result.setComparisonStatus("❌ Transaction not found");
        result.setTransactionId(message.getPid());
        try {
            // Query Redis by transaction ID
            Object redisTransaction = transactionRedisService.getData(message.getPid().toString());
            if (redisTransaction != null) {
                try {
                    ObjectMapper objectMapper = new ObjectMapper();
                    // Parse the Redis transaction JSON
                    Transaction transaction = objectMapper.readValue(String.valueOf(redisTransaction), Transaction.class);

                    // Get the amount from the received transaction
                    double receivedAmount = message.getpAmount();

                    // Get the amount from the Redis transaction
                    double redisAmount = transaction.getAmount();
                    result.setOriginalAmount(redisAmount);
                    result.setReceivedAmount(receivedAmount);
                    // Compare the amounts
                    if (receivedAmount == redisAmount) {
                        result.setComparisonStatus("✅ Amounts are equal");
                   
                    } else {
                        result.setComparisonStatus("⚠️ Amounts differ");
                       
                    }

                } catch (Exception e) {
                    // Handle the exception
                	logger.error("Error occurred while comparing transactions: " + e.getMessage());
                }
            }
            


        } catch (Exception ex) {
            // Handle the exception
        	logger.error("Error occurred while querying Redis transaction data: " + ex.getMessage());
           
        }
        return result;
    }

}
