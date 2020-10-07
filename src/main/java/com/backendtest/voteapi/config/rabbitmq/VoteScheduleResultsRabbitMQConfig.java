package com.backendtest.voteapi.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VoteScheduleResultsRabbitMQConfig {
  @Bean
  TopicExchange voteScheduleResultsTopic() {
    String exchangeName = ExchangeEnum.VOTE_SESSION_RESULTS_TOPIC.getName();
    return new TopicExchange(exchangeName);
  }

  @Bean
  Queue voteScheduleResultsQueue() {
    String queueName = QueueEnum.VOTE_SESSION_RESULTS_QUEUE.getName();
    return new Queue(queueName, true);
  }

  @Bean
  Binding bindResultsQueue(Queue voteScheduleResultsQueue, TopicExchange voteScheduleResultsTopic) {
    return BindingBuilder.bind(voteScheduleResultsQueue).to(voteScheduleResultsTopic).with("#");
  }
}
