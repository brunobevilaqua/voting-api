package com.backendtest.voteapi.config.rabbitmq;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum QueueEnum {
  VOTE_SESSION_PROCESSING_QUEUE("vote-session-processing-queue"),
  VOTE_SESSION_RESULTS_QUEUE("vote-session-results-queue"),
  VOTE_SESSION_PROCESSING_FAIL_QUEUE("vote-session-processing-fail-queue");

  private String name;
}
