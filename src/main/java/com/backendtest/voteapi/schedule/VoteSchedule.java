package com.backendtest.voteapi.schedule;

import com.backendtest.voteapi.shared.audit.Audit;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "VOTE_SCHEDULE")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class VoteSchedule extends Audit implements Serializable {

  @Id
  @Column(name = "VOTE_SCHEDULE")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long voteScheduleId;

  @Column(name = "VOTING_SCHEDULE_DESCRIPTION")
  private String voteScheduleDescription;
}
