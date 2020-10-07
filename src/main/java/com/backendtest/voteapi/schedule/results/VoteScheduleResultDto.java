package com.backendtest.voteapi.schedule.results;

import java.time.ZonedDateTime;
import javax.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VoteScheduleResultDto {
  private Long voteScheduleResultId;

  private Long voteSessionId;

  private Integer totalOfYesVotes;

  private Integer totalOfNoVotes;

  @Nullable
  private ZonedDateTime createDate;

  @Nullable
  private ZonedDateTime updateDate;
}
