package com.backendtest.voteapi.schedule.vote;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VoteMapper {

    @Mapping(source = "voteOption", target = "voteOption")
    @Mapping(source = "cpf", target = "cpf")
    @Mapping(source = "voteSessionId", target = "voteSession.voteSessionId")
    Vote map(VoteDto source);

}
