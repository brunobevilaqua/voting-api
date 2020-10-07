package com.backendtest.voteapi.schedule.vote;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum VoteOption {
  YES("YES"),
  NO("NO");

  private String option;
}
