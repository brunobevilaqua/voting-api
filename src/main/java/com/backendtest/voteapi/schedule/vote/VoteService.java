package com.backendtest.voteapi.schedule.vote;

import com.backendtest.voteapi.associate.AssociateService;
import com.backendtest.voteapi.schedule.session.VoteSession;
import com.backendtest.voteapi.schedule.session.VoteSessionService;
import com.backendtest.voteapi.shared.MessageResolverConfig;
import com.backendtest.voteapi.shared.exception.EntityNotUpdateableException;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;;

@Service
@AllArgsConstructor
public class VoteService {

  private VoteRepository repository;

  private VoteSessionService voteSessionService;

  private AssociateService associateService;

  private MessageResolverConfig messageResolver;

  @Transactional
  public Vote submitVote(VoteDto dto) {
    VoteSession voteSession = voteSessionService.findBydId(dto.getVoteSessionId());
    if (!associateService.isAssociateAbleToVote(dto.getCpf())) {
      throw new EntityNotUpdateableException(messageResolver.get("associate.not.able.to.vote"));
    }

    if (!voteSession.isVotingSessionStillOpen()) {
      throw new EntityNotUpdateableException(messageResolver.get("vote.session.closed"));
    }

    if (repository
        .findByCpfAndVoteSession_VoteSessionId(dto.getCpf(), dto.getVoteSessionId())
        .isPresent()) {
      throw new EntityNotUpdateableException(messageResolver.get("associate.already.voted"));
    }

    Vote vote =
        Vote.builder()
            .voteOption(dto.getVoteOption())
            .cpf(dto.getCpf())
            .voteSession(voteSession)
            .build();

    return repository.save(vote);
  }

  public List<Vote> findVotesForVoteSessionId(Long voteSessionId) {
    return repository.findByVoteSession_VoteSessionId(voteSessionId);
  }
}
