package com.backendtest.voteapi.config.rabbitmq;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExchangeEnum {
  VOTE_SESSION_TOPIC("vote-session-topic"),
  VOTE_SESSION_RESULTS_TOPIC("vote-session-results-topic");

  private String name;
}
