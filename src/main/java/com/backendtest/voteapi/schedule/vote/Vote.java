package com.backendtest.voteapi.schedule.vote;

import com.backendtest.voteapi.schedule.session.VoteSession;
import com.backendtest.voteapi.shared.audit.Audit;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "VOTE")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Vote extends Audit implements Serializable {
  @Id
  @Column(name = "VOTE_ID")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long voteId;

  @Column(name = "CPF")
  private String cpf;

  @ToString.Exclude
  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "VOTE_SESSION_ID")
  private VoteSession voteSession;

  @Enumerated(EnumType.STRING)
  @Column(name = "VOTE")
  private VoteOption voteOption;
}
