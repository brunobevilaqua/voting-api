package com.backendtest.votingapi.schedule;

import com.backendtest.voteapi.schedule.VoteSchedule;
import com.backendtest.voteapi.schedule.VoteScheduleMapper;
import com.backendtest.voteapi.schedule.VoteScheduleRepository;
import com.backendtest.voteapi.schedule.VoteScheduleService;
import com.backendtest.voteapi.shared.exception.EntityNotFoundException;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

@RunWith(MockitoJUnitRunner.class)
public class VoteScheduleServiceTest {

  @Autowired private VoteScheduleService voteScheduleService;

  @Mock private VoteScheduleRepository voteScheduleRepository;

  private VoteScheduleMapper voteScheduleMapper;

  @Before
  public void setup() {
    voteScheduleService = new VoteScheduleService(voteScheduleMapper, voteScheduleRepository);
  }

  @Test
  public void returnVoteSchedule_whenSearchingByValidId() {
    VoteSchedule voteSchedule = VoteSchedule.builder().voteScheduleId(1L).build();
    Mockito.when(voteScheduleRepository.findById(1L)).thenReturn(Optional.of(voteSchedule));
    Assert.assertNotNull(voteScheduleService.findVoteScheduleById(1L));
  }

  @Test
  public void returnException_whenSearchingByInvalidId() {
    Mockito.when(voteScheduleRepository.findById(1L)).thenThrow(new EntityNotFoundException());
    Assert.assertThrows(
        EntityNotFoundException.class, () -> voteScheduleService.findVoteScheduleById(1L));
  }
}
