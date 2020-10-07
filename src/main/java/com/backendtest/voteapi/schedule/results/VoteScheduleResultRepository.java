package com.backendtest.voteapi.schedule.results;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteScheduleResultRepository extends JpaRepository<VoteScheduleResult, Long> {

  Optional<VoteScheduleResult> findByVoteSessionVoteSessionId(Long voteSessionId);
}
