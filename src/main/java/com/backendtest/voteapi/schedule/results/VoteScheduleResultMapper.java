package com.backendtest.voteapi.schedule.results;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VoteScheduleResultMapper {

  @Mapping(source = "voteSessionId", target = "voteSession.voteSessionId")
  VoteScheduleResult map(VoteScheduleResultDto source);

  @Mapping(source = "voteSession.voteSessionId", target = "voteSessionId")
  VoteScheduleResultDto map(VoteScheduleResult source);
}
