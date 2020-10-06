package com.backendtest.voteapi.schedule.vote;

import com.backendtest.voteapi.shared.validation.CheckCpf;
import com.backendtest.voteapi.shared.validation.Validate;
import com.backendtest.voteapi.shared.validation.CheckVote;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VoteDto {

  @NotNull
  private String cpf;

  @NotNull
  @CheckVote(groups = Validate.OnCreate.class)
  private VoteOption voteOption;

  @NotNull(groups = Validate.OnCreate.class)
  private Long voteSessionId;
}
