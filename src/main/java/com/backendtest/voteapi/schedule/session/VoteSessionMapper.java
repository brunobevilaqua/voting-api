package com.backendtest.voteapi.schedule.session;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VoteSessionMapper {

  @Mapping(source = "voteScheduleId", target = "voteSchedule.voteScheduleId")
  @Mapping(source = "voteSessionResultsProcessed", target = "voteSessionResultsProcessed")
  @Mapping(source = "voteSessionResultsNotified", target = "voteSessionResultsNotified")
  @Mapping(source = "voteSessionStatus", target = "voteSessionStatusEnum")
  VoteSession map(VoteSessionDto source);

  @Mapping(source = "voteSchedule.voteScheduleId", target = "voteScheduleId")
  @Mapping(source = "voteSessionResultsProcessed", target = "voteSessionResultsProcessed")
  @Mapping(source = "voteSessionResultsNotified", target = "voteSessionResultsNotified")
  @Mapping(source = "voteSessionStatusEnum", target = "voteSessionStatus")
  VoteSessionDto map(VoteSession source);
}
