package com.backendtest.voteapi.schedule;

import com.backendtest.voteapi.shared.exception.EntityNotFoundException;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;;

@Service
@AllArgsConstructor
public class VoteScheduleService {

  private VoteScheduleMapper mapper;

  private VoteScheduleRepository repository;

  @Transactional
  public VoteSchedule createVoteSchedule(VoteScheduleDto dto) {
    VoteSchedule voteSchedule = mapper.map(dto);
    return repository.save(voteSchedule);
  }

  public VoteSchedule findVoteScheduleById(Long voteScheduleId) {
    return repository.findById(voteScheduleId)
        .orElseThrow(EntityNotFoundException::new);
  }

  public List<VoteSchedule> findAllVoteSchedule() {
    List<VoteSchedule> voteScheduleList = repository.findAll();
    return voteScheduleList;
  }
}
