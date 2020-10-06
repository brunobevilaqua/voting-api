package com.backendtest.voteapi.schedule.results;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Vote Schedule Results")
@RestController
@AllArgsConstructor
public class VoteScheduleResultController {

  private VoteScheduleResultService service;

  @ApiOperation(value = "Returns Vote Schedule Results")
  @ApiResponses(
      value = {
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(
            code = 422,
            message = "UNPROCESSABLE_ENTITY (This is related to business rules)"),
      })
  @GetMapping(value = "/voteschedules/{id}/results")
  public ResponseEntity<VoteScheduleResult> findVoteScheduleResultByVoteSessionId(
      @ApiParam(value = "Vote Session Id") @PathVariable("id") Long voteSessionId) {
    return ResponseEntity.ok().body(service.findVoteScheduleResultByVoteSessionId(voteSessionId));
  }
}
