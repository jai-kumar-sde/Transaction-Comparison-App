package com.transactionservices.TransactionComparisonApplication.Configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    // Initialize logger for logging purposes
    private static final Logger logger = LoggerFactory.getLogger(WebSocketConfig.class);

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        try {
     
            registry.addEndpoint("/websocket").withSockJS();
            
        } catch (Exception e) {
            // Handle any exceptions that occur during endpoint registration
            logger.error("Error while registering Stomp endpoints: " + e.getMessage());
        }
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        try {
            // Configure message broker for handling message routing
            registry.enableSimpleBroker("/topic");
            registry.setApplicationDestinationPrefixes("/app");
        } catch (Exception e) {
            // Handle any exceptions that occur during message broker configuration
            logger.error("Error while configuring message broker: " + e.getMessage());
        }
    }
}
