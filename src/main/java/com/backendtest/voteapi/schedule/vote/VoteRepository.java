package com.backendtest.voteapi.schedule.vote;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;;

public interface VoteRepository extends JpaRepository<Vote, Long> {

  Optional<Vote> findByCpfAndVoteSession_VoteSessionId(String cpf, Long voteSessionId);

  List<Vote> findByVoteSession_VoteSessionId(Long voteSessionId);
}
