package com.backendtest.voteapi.schedule.session;

import com.backendtest.voteapi.schedule.VoteSchedule;
import com.backendtest.voteapi.shared.audit.Audit;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.time.Instant;
import java.time.ZonedDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "VOTE_SESSION")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(VoteSessionListener.class)
public class VoteSession extends Audit implements Serializable {

  @Id
  @Column(name = "VOTE_SESSION_ID")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long voteSessionId;

  @Column(name = "VOTE_SESSION_DURATION_MINUTES")
  private Integer voteSessionDuration;

  @Column(name = "VOTE_SESSION_START_TIME")
  private ZonedDateTime voteSessionStartTime;

  @Column(name = "VOTE_SESSION_END_TIME")
  private ZonedDateTime voteSessionEndTime;

  @ToString.Exclude
  @JsonIgnore
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "VOTE_SCHEDULE_ID")
  private VoteSchedule voteSchedule;

  @Enumerated(EnumType.STRING)
  @Column(name = "VOTE_SESSION_STATUS")
  private VoteSessionStatusEnum voteSessionStatusEnum;

  @Type(type="yes_no")
  @Column(name = "VOTE_SESSION_RESULTS_PROCESSED")
  private Boolean voteSessionResultsProcessed;

  @Type(type="yes_no")
  @Column(name = "VOTE_SESSION_RESULTS_NOTIFIED")
  private Boolean voteSessionResultsNotified;

  public boolean isVotingSessionStillOpen() {
    return Instant.now().isBefore(this.voteSessionEndTime.toInstant());
  }

  public boolean isVotingSessionClosed() {
    return Instant.now().isAfter(this.voteSessionEndTime.toInstant());
  }

  public VoteSessionStatusEnum getVoteSessionStatus() {
    return isVotingSessionStillOpen() ? VoteSessionStatusEnum.OPEN : VoteSessionStatusEnum.CLOSED;
  }
}
