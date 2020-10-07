package com.backendtest.voteapi.schedule;

import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VoteScheduleMapper {

  VoteSchedule map(VoteScheduleDto source);

  VoteScheduleDto map(VoteSchedule source);

  default List<VoteScheduleDto> map(List<VoteSchedule> list) {
    return list.stream().map(this::map).collect(Collectors.toList());
  }
}
