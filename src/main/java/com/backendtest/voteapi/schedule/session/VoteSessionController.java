package com.backendtest.voteapi.schedule.session;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Api(tags = "Votes Sessions")
@RestController
@AllArgsConstructor
public class VoteSessionController {

  private VoteSessionService service;

  @ApiOperation(value = "Find Vote Session By Vote Schedule Id")
  @ApiResponses(
      value = {
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(
            code = 422,
            message = "UNPROCESSABLE_ENTITY (This is related to business rules)"),
      })
  @GetMapping(value = "/votesessions")
  public ResponseEntity<VoteSessionDto> findVoteSessionByVoteScheduleId(
      @RequestParam(value = "votescheduleid", required = true) Long voteScheduleId) {
    return ResponseEntity.ok().body(service.findVoteSessionByScheduleId(voteScheduleId));
  }

  @ApiOperation(value = "Find Vote Session By Vote Session Id")
  @GetMapping(value = "/votesessions/{id}")
  public ResponseEntity<VoteSessionDto> findVoteSessionStatusById(
      @ApiParam(value = "Vote Session Id") @PathVariable("id") Long voteSessionId) {
    return ResponseEntity.ok().body(service.findVoteSessionById(voteSessionId));
  }

  @ApiOperation(
      value = "Create Vote Session",
      notes =
          "voteSessionDuration will be considered in 'minutes', when not provided will be set a default of 60min",
      response = VoteSession.class)
  @PostMapping(value = "/votesessions")
  public ResponseEntity<VoteSession> createVoteSession(@RequestBody VoteSessionDto dto) {
    VoteSession voteSession = service.createVoteSession(dto);
    return ResponseEntity.created(
            ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/id")
                .buildAndExpand(voteSession.getVoteSessionId())
                .toUri())
        .body(voteSession);
  }
}
