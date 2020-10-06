package com.backendtest.voteapi.worker;

import com.backendtest.voteapi.config.rabbitmq.ExchangeEnum;
import com.backendtest.voteapi.schedule.session.VoteSessionService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ProcessVoteSession {
  private static String exchange = ExchangeEnum.VOTE_SESSION_TOPIC.getName();

  private RabbitTemplate rabbitTemplate;

  private VoteSessionService voteSessionService;

  @Scheduled(fixedRate = 60)
  public void startProcessingVoteSession() {
    sendMessageToExchange();
  }

  private void sendMessageToExchange() {
    voteSessionService.fetchListOfClosedVoteSession().stream()
        .forEach(
            voteSessionToProcess -> {
              String voteSessionId = voteSessionToProcess.getVoteSessionId().toString();
              String routingKey = "voteSession." + voteSessionId.toString();
              rabbitTemplate.convertAndSend(exchange, routingKey, voteSessionId.toString());
            });
  }
}
