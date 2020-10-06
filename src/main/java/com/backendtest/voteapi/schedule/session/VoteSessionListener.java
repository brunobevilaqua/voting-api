package com.backendtest.voteapi.schedule.session;

import java.time.ZonedDateTime;
import javax.persistence.PrePersist;

public class VoteSessionListener {

  private static final Integer DEFAULT_DURATION_TIME = 1;

  @PrePersist
  public void prePersist(VoteSession voteSession) {
    voteSession.setVoteSessionStartTime(ZonedDateTime.now());

    if (voteSession.getVoteSessionDuration() != null && voteSession.getVoteSessionDuration() != 0) {
    } else {
      voteSession.setVoteSessionDuration(DEFAULT_DURATION_TIME);
    }

    voteSession.setVoteSessionEndTime(
        ZonedDateTime.now().plusMinutes(voteSession.getVoteSessionDuration()));
  }
}
