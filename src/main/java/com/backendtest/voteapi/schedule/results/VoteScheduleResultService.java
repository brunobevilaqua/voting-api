package com.backendtest.voteapi.schedule.results;

import com.backendtest.voteapi.schedule.vote.VoteOption;
import com.backendtest.voteapi.schedule.vote.VoteService;
import com.backendtest.voteapi.shared.exception.EntityNotFoundException;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VoteScheduleResultService {

  private VoteScheduleResultRepository repository;

  private VoteService voteService;

  public VoteScheduleResult findVoteScheduleResultByVoteSessionId(Long voteSessionId) {
    return repository
        .findByVoteSessionVoteSessionId(voteSessionId)
        .orElseThrow(EntityNotFoundException::new);
  }

  /**
   * Calculates VoteScheduleResults for Already Closed Sessions.
   *
   * @param voteSessionId
   */
  public VoteScheduleResult calculateAndSaveVoteResultsByVoteSessionId(Long voteSessionId) {
    VoteScheduleResult voteScheduleResult = new VoteScheduleResult();

    voteService.findVotesForVoteSessionId(voteSessionId).stream()
        .forEach(vote -> incrementVoteResult(voteScheduleResult, vote.getVoteOption()));

    return repository.save(voteScheduleResult);
  }

  private void incrementVoteResult(VoteScheduleResult voteScheduleResult, VoteOption voteOption) {
    Integer totalOfNoVotes = Optional.ofNullable(voteScheduleResult.getTotalOfNoVotes()).orElse(0);
    Integer totalOfYesVote = Optional.ofNullable(voteScheduleResult.getTotalOfYesVotes()).orElse(0);

    if (VoteOption.NO.equals(voteOption)) {
      voteScheduleResult.setTotalOfNoVotes(totalOfNoVotes + 1);
    } else {
      voteScheduleResult.setTotalOfYesVotes(totalOfYesVote + 1);
    }
  }
}
