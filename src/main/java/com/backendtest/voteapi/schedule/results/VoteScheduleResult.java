package com.backendtest.voteapi.schedule.results;

import com.backendtest.voteapi.schedule.session.VoteSession;
import com.backendtest.voteapi.shared.audit.Audit;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "VOTE_SCHEDULE_RESULT")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VoteScheduleResult extends Audit implements Serializable {

  @Id
  @Column(name = "VOTE_SCHEDULE_RESULT_ID")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long voteScheduleResultId;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "VOTE_SESSION_ID")
  private VoteSession voteSession;

  @Column(name = "TOTAL_OF_YES_VOTES")
  private Integer totalOfYesVotes;

  @Column(name = "TOTAL_OF_NO_VOTES")
  private Integer totalOfNoVotes;

}
