package com.backendtest.votingapi.schedule.vote;

import com.backendtest.voteapi.associate.AssociateService;
import com.backendtest.voteapi.schedule.session.VoteSessionService;
import com.backendtest.voteapi.schedule.vote.Vote;
import com.backendtest.voteapi.schedule.vote.VoteRepository;
import com.backendtest.voteapi.schedule.vote.VoteService;
import com.backendtest.voteapi.shared.MessageResolverConfig;
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
public class VoteServiceTest {
  @Mock private VoteRepository repository;

  @MockBean private VoteSessionService voteSessionService;

  @MockBean private AssociateService associateService;

  @MockBean private MessageResolverConfig messageResolver;

  @Autowired private VoteService serviceToTest;

    @Before
    public void setup(){
      serviceToTest = new VoteService(repository,voteSessionService,associateService,messageResolver);
    }

    @Test
    public void returnVotes_whenSendingValidVoteSessionId() {
      Mockito.when(repository.findByVoteSession_VoteSessionId(1L)).thenReturn(Mockito.anyList());
      Assert.assertNotNull(serviceToTest.findVotesForVoteSessionId(1L));
    }

}
