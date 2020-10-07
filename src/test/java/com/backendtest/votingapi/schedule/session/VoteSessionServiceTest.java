package com.backendtest.votingapi.schedule.session;

import com.backendtest.voteapi.schedule.session.VoteSession;
import com.backendtest.voteapi.schedule.session.VoteSessionDto;
import com.backendtest.voteapi.schedule.session.VoteSessionMapper;
import com.backendtest.voteapi.schedule.session.VoteSessionRepository;
import com.backendtest.voteapi.schedule.session.VoteSessionService;
import com.backendtest.voteapi.shared.MessageResolverConfig;
import com.backendtest.voteapi.shared.exception.EntityNotFoundException;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.testng.Assert;

@RunWith(MockitoJUnitRunner.class)
public class VoteSessionServiceTest {
  @Mock private VoteSessionRepository repository;

  @Mock private VoteSessionMapper mapper;

  @MockBean private MessageResolverConfig messageResolver;

  @Autowired private VoteSessionService serviceToTest;

  @Before
  public void setup() {
    serviceToTest = new VoteSessionService(repository, mapper, messageResolver);
  }

  @Test
  public void returnData_whenSendingValidVoteScheduleId() {
    VoteSession voteSession = VoteSession.builder().voteSessionId(1L).build();
    VoteSessionDto sessionDto = VoteSessionDto.builder().voteSessionId(1L).build();
    Mockito.when(repository.findByVoteSchedule_VoteScheduleId(1L))
        .thenReturn(Optional.of(voteSession));
    Mockito.when(mapper.map(voteSession)).thenReturn(sessionDto);

    Assert.assertNotNull(serviceToTest.findVoteSessionByScheduleId(1L));
  }

  @Test
  public void throwException_whenSendingInvalidValidVoteScheduleId() {
    VoteSession response = VoteSession.builder().voteSessionId(1L).build();
    Mockito.when(repository.findByVoteSchedule_VoteScheduleId(1L))
        .thenThrow(new EntityNotFoundException());
    Assert.assertThrows(
        EntityNotFoundException.class, () -> serviceToTest.findVoteSessionByScheduleId(1L));
  }

  @Test
  public void returnData_whenSendingValidId() {
    VoteSession voteSession = VoteSession.builder().voteSessionId(1L).build();
    Mockito.when(repository.findByVoteSessionId(1L))
        .thenReturn(Optional.of(voteSession));
    Assert.assertNotNull(serviceToTest.findBydId(1L));
  }
}
