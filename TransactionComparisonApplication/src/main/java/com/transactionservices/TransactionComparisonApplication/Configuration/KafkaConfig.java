package com.transactionservices.TransactionComparisonApplication.Configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

  @Bean
  public NewTopic taskTopic() {
    return TopicBuilder.name("task-topic")
        .partitions(1)
        .replicas(1)
        .build();
  }
}
