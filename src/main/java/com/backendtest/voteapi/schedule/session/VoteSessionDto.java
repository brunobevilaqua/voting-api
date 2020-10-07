package com.backendtest.voteapi.schedule.session;

import com.backendtest.voteapi.shared.validation.Validate;
import io.swagger.annotations.ApiModelProperty;
import java.time.ZonedDateTime;
import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VoteSessionDto {

  @ApiModelProperty(required = true, value = "")
  @NotNull(groups = Validate.OnCreate.class)
  private Long voteScheduleId;

  private Long voteSessionId;

  @Nullable private Integer voteSessionDuration;

  @Nullable private ZonedDateTime voteSessionStartTime;

  @Nullable private ZonedDateTime voteSessionEndTime;

  @Nullable
  private ZonedDateTime createDate;

  @Nullable
  private ZonedDateTime updateDate;

  @Nullable
  private Boolean voteSessionResultsProcessed;

  @Nullable
  private Boolean voteSessionResultsNotified;

  @Nullable
  private VoteSessionStatusEnum voteSessionStatus;
}
