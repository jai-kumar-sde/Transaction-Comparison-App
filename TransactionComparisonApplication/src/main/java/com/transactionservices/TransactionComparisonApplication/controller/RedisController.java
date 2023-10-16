package com.transactionservices.TransactionComparisonApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transactionservices.TransactionComparisonApplication.service.TransactionRedisService;

@RestController
@RequestMapping("/data")
public class RedisController {

    @Autowired
    private TransactionRedisService redisService;

    /**
     * Endpoint to create data in Redis.
     *
     * @param key   the key to store data
     * @param value the value to be stored
     * @return ResponseEntity with success or error message
     */
    @PostMapping("/{key}")
    public ResponseEntity<String> createData(@PathVariable String key, @RequestBody String value) {
        try {
            redisService.createData(key, value);
            return ResponseEntity.ok("Data created successfully");
        } catch (Exception e) {
            // Log the exception and return an internal server error message
            // logger.error("Error creating data", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating data");
        }
    }

    /**
     * Endpoint to retrieve data from Redis.
     *
     * @param key the key to retrieve data
     * @return ResponseEntity with the retrieved data or error message
     */
    @GetMapping("/{key}")
    public ResponseEntity<Object> getData(@PathVariable String key) {
        try {
            Object data = redisService.getData(key);
            if (data != null) {
                return ResponseEntity.ok(data);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Data not found");
            }
        } catch (Exception e) {
            // Log the exception and return an internal server error message
            // logger.error("Error retrieving data", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving data");
        }
    }

    /**
     * Endpoint to update data in Redis.
     *
     * @param key   the key to update data
     * @param value the value to be updated
     * @return ResponseEntity with success or error message
     */
    @PutMapping("/{key}")
    public ResponseEntity<String> updateData(@PathVariable String key, @RequestBody String value) {
        try {
            redisService.updateData(key, value);
            return ResponseEntity.ok("Data updated successfully");
        } catch (Exception e) {
            // Log the exception and return an internal server error message
            // logger.error("Error updating data", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating data");
        }
    }

    /**
     * Endpoint to delete data from Redis.
     *
     * @param key the key to delete data
     * @return ResponseEntity with success or error message
     */
    @DeleteMapping("/{key}")
    public ResponseEntity<String> deleteData(@PathVariable String key) {
        try {
            redisService.deleteData(key);
            return ResponseEntity.ok("Data deleted successfully");
        } catch (Exception e) {
            // Log the exception and return an internal server error message
            // logger.error("Error deleting data", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting data");
        }
    }
}