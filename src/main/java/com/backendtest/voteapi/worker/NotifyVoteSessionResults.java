package com.backendtest.voteapi.worker;

import com.backendtest.voteapi.config.rabbitmq.ExchangeEnum;
import com.backendtest.voteapi.config.rabbitmq.QueueEnum;
import com.backendtest.voteapi.schedule.results.VoteScheduleResult;
import com.backendtest.voteapi.schedule.results.VoteScheduleResultService;
import com.backendtest.voteapi.schedule.session.VoteSessionService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@AllArgsConstructor
public class NotifyVoteSessionResults {
  private static String exchange = ExchangeEnum.VOTE_SESSION_RESULTS_TOPIC.getName();
  private static String ddq = QueueEnum.VOTE_SESSION_PROCESSING_FAIL_QUEUE.getName();

  private AmqpTemplate amqpTemplate;

  private VoteScheduleResultService voteScheduleResultService;

  private VoteSessionService voteSessionService;

  @RabbitListener(queues = "vote-session-processing-queue")
  private void consume(Long voteSessionId) {
    processResults(voteSessionId);
  }

  @Transactional
  private void processResults(Long voteSessionId) {
    VoteScheduleResult voteScheduleResult =
        voteScheduleResultService.calculateAndSaveVoteResultsByVoteSessionId(voteSessionId);

    voteSessionService.updateVoteSessionNotifiedStatus(voteSessionId);

    publish(voteScheduleResult);
  }

  private void publish(VoteScheduleResult voteScheduleResult) {
    String routingKey = "voteSchedule." + voteScheduleResult.getVoteScheduleResultId().toString();
    try {
      amqpTemplate.convertAndSend(exchange, routingKey, voteScheduleResult.toString());
    } catch (Exception e) {
      amqpTemplate.convertAndSend(ddq, voteScheduleResult.toString());
    }
  }

}
