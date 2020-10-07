package com.backendtest.votingapi.schedule.results;

import com.backendtest.voteapi.schedule.results.VoteScheduleResult;
import com.backendtest.voteapi.schedule.results.VoteScheduleResultRepository;
import com.backendtest.voteapi.schedule.results.VoteScheduleResultService;
import com.backendtest.voteapi.schedule.vote.VoteService;
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
import org.springframework.boot.test.mock.mockito.MockBean;

@RunWith(MockitoJUnitRunner.class)
public class VoteScheduleResultServiceTest {

  @Autowired private VoteScheduleResultService serviceToTest;

  @Mock private VoteScheduleResultRepository repository;

  @MockBean private VoteService voteService;

  @Before
  public void setup() {
    serviceToTest = new VoteScheduleResultService(repository, voteService);
  }

  @Test
  public void returnResults_whenSendingValidVoteSessionId() {
    VoteScheduleResult response = new VoteScheduleResult();
    Mockito.when(repository.findByVoteSessionVoteSessionId(1L)).thenReturn(Optional.of(response));
    Assert.assertNotNull(serviceToTest.findVoteScheduleResultByVoteSessionId(1L));
  }

  @Test
  public void throwException_whenSendingInvalidValidVoteSessionId() {
    VoteScheduleResult response = new VoteScheduleResult();
    Mockito.when(repository.findByVoteSessionVoteSessionId(1L))
        .thenThrow(new EntityNotFoundException());
    Assert.assertThrows(
        EntityNotFoundException.class,
        () -> serviceToTest.findVoteScheduleResultByVoteSessionId(1L));
  }
}
