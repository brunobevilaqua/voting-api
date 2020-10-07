package com.backendtest.voteapi.schedule.session;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteSessionRepository extends JpaRepository<VoteSession, Long> {

  Optional<VoteSession> findByVoteSchedule_VoteScheduleId(Long voteScheduleId);

  Optional<VoteSession> findByVoteSessionId(Long voteSessionId);

  List<VoteSession>
      findByVoteSessionResultsProcessedAndVoteSessionResultsNotifiedAndVoteSessionStatusEnum(
          Boolean voteSessionResultsProcessed,
          Boolean VoteSessionResultsNotified,
          VoteSessionStatusEnum voteSessionStatusEnum);
}
