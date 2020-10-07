package com.backendtest.voteapi.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VoteScheduleRabbitMQConfig {
  @Bean
  Queue voteScheduleProcessingQueue() {
    String queueName = QueueEnum.VOTE_SESSION_PROCESSING_QUEUE.getName();
    return new Queue(queueName, true);
  }

  @Bean
  TopicExchange voteScheduleProcessingExchange() {
    String exchangeName = ExchangeEnum.VOTE_SESSION_TOPIC.getName();
    return new TopicExchange(exchangeName);
  }

  @Bean
  Queue failQueue() {
    String queueName = QueueEnum.VOTE_SESSION_PROCESSING_FAIL_QUEUE.getName();
    return new Queue(queueName, true);
  }

  @Bean
  Binding bindingProcessingQueue(
      Queue voteScheduleProcessingQueue, TopicExchange voteScheduleProcessingExchange) {
    return BindingBuilder.bind(voteScheduleProcessingQueue)
        .to(voteScheduleProcessingExchange)
        .with("#");
  }
}
