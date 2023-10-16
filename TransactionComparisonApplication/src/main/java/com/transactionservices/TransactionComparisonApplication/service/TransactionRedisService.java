package com.transactionservices.TransactionComparisonApplication.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public class TransactionRedisService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionRedisService.class);

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * Create operation
     *
     * @param key   The key to store the data
     * @param value The value to be stored
     */
    public void createData(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
        } catch (Exception e) {
            LOGGER.error("An error occurred during the creation of data for key: {}", key, e);
            // Add additional exception handling logic if required
        }
    }

    /**
     * Read operation
     *
     * @param key The key to retrieve the data
     * @return The object associated with the key, or null if not found
     */
    public Object getData(String key) {
        try {
            return redisTemplate.opsForValue().get(key);
        } catch (Exception e) {
            LOGGER.error("An error occurred during the retrieval of data for key: {}", key, e);
            // Add additional exception handling logic if required
            return null;
        }
    }

    /**
     * Update operation
     *
     * @param key       The key to update
     * @param newValue  The new value to be set
     */
    public void updateData(String key, Object newValue) {
        try {
            if (redisTemplate.hasKey(key)) {
                redisTemplate.opsForValue().set(key, newValue);
            }
        } catch (Exception e) {
            LOGGER.error("An error occurred during the update of data for key: {}", key, e);
            // Add additional exception handling logic if required
        }
    }

    /**
     * Delete operation
     *
     * @param key The key to delete
     */
    public void deleteData(String key) {
        try {
            redisTemplate.delete(key);
        } catch (Exception e) {
            LOGGER.error("An error occurred during the deletion of data for key: {}", key, e);
            // Add additional exception handling logic if required
        }
    }
}
