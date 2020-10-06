package com.backendtest.voteapi.schedule.session;

import com.backendtest.voteapi.shared.MessageResolverConfig;
import com.backendtest.voteapi.shared.exception.EntityNotFoundException;
import com.backendtest.voteapi.shared.exception.EntityNotUpdateableException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;;

@Service
@AllArgsConstructor
public class VoteSessionService {

  private VoteSessionRepository repository;

  private VoteSessionMapper mapper;

  private MessageResolverConfig messageResolver;

  @Transactional
  public VoteSession createVoteSession(VoteSessionDto dto) {
    Optional<VoteSession> voteSession =
        repository.findByVoteSchedule_VoteScheduleId(dto.getVoteScheduleId());

    if (voteSession.isPresent()) {
      throw new EntityNotUpdateableException(
          messageResolver.get("vote.session.already.exists", voteSession.get().getVoteSessionId()));
    }

    VoteSession voteSessionToCreate = mapper.map(dto);
    voteSessionToCreate.setVoteSessionResultsNotified(Boolean.FALSE);
    voteSessionToCreate.setVoteSessionResultsProcessed(Boolean.FALSE);
    voteSessionToCreate.setVoteSessionStatusEnum(VoteSessionStatusEnum.OPEN);
    return repository.save(voteSessionToCreate);
  }

  public VoteSessionDto findVoteSessionByScheduleId(Long voteScheduleId) {
    return repository
        .findByVoteSchedule_VoteScheduleId(voteScheduleId)
        .map(response -> mapper.map(response))
        .orElseThrow(
            () -> new EntityNotFoundException(messageResolver.get("vote.session.not.found")));
  }

  public VoteSessionDto findVoteSessionById(Long voteSessionId) {
    return repository
        .findByVoteSessionId(voteSessionId)
        .map(response -> mapper.map(response))
        .orElseThrow(
            () -> new EntityNotFoundException(messageResolver.get("vote.session.not.found")));
  }

  public VoteSession findBydId(Long voteSessionId) {
    return repository
        .findByVoteSessionId(voteSessionId)
        .orElseThrow(
            () -> new EntityNotFoundException(messageResolver.get("vote.session.not.found")));
  }

  @Transactional
  public List<VoteSession> fetchListOfClosedVoteSession() {
    return repository
        .findByVoteSessionResultsProcessedAndVoteSessionResultsNotifiedAndVoteSessionStatusEnum(
            Boolean.FALSE, Boolean.FALSE, VoteSessionStatusEnum.OPEN)
        .stream()
        .filter(VoteSession::isVotingSessionClosed)
        .map(
            voteSession -> {
              voteSession.setVoteSessionStatusEnum(VoteSessionStatusEnum.CLOSED);
              voteSession.setVoteSessionResultsProcessed(Boolean.TRUE);
              return voteSession;
            })
        .map(voteSession -> repository.save(voteSession))
        .collect(Collectors.toList());
  }

  public void updateVoteSessionNotifiedStatus(Long voteSessionId) {
    repository
        .findById(voteSessionId)
        .map(
            voteSession -> {
              voteSession.setVoteSessionResultsNotified(Boolean.TRUE);
              return voteSession;
            })
        .map(voteSession -> repository.save(voteSession));
  }
}
