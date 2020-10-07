package com.backendtest.voteapi.schedule;

import com.backendtest.voteapi.shared.validation.Validate;
import java.time.ZonedDateTime;
import javax.annotation.Nullable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VoteScheduleDto {

  @Nullable private String voteScheduleId;

  @NotNull(groups = Validate.OnCreate.class)
  @NotEmpty(groups = Validate.OnCreate.class)
  private String voteScheduleDescription;

  @Nullable
  private ZonedDateTime createDate;

  @Nullable
  private ZonedDateTime updateDate;
}
