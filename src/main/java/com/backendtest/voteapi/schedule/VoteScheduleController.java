package com.backendtest.voteapi.schedule;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Api(tags = "Vote Schedules")
@RestController
@AllArgsConstructor
public class VoteScheduleController {

  private VoteScheduleService service;

  @ApiOperation(
      value = "Create Agenda",
      notes = "Creates a new Agenda that can be voted later by creating a Voting Session.")
  @ApiResponses(
      value = {
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(
            code = 422,
            message = "UNPROCESSABLE_ENTITY (This is related to business rules)"),
      })
  @PostMapping(value = "/voteschedules")
  public ResponseEntity<VoteSchedule> createVoteSchedule(
      @ApiParam(value = "Vote Schedule Object", required = true) @RequestBody
          VoteScheduleDto voteScheduleDto) {
    VoteSchedule schedule = service.createVoteSchedule(voteScheduleDto);
    return ResponseEntity.created(
            ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/id")
                .buildAndExpand(schedule.getVoteScheduleId())
                .toUri())
        .body(schedule);
  }

  @ApiOperation(value = "Find Vote Schedule by Id")
  @ApiResponses(
      value = {
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(
            code = 422,
            message = "UNPROCESSABLE_ENTITY (This is related to business rules)"),
      })
  @GetMapping(value = "/voteschedules/{id}")
  public ResponseEntity<VoteSchedule> findVoteScheduleById(
      @PathVariable("id") Long voteScheduleId) {
    return ResponseEntity.ok().body(service.findVoteScheduleById(voteScheduleId));
  }

  @ApiOperation(value = "Find All Vote Schedules")
  @ApiResponses(
      value = {
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(
            code = 422,
            message = "UNPROCESSABLE_ENTITY (This is related to business rules)"),
      })
  @GetMapping(value = "/voteschedules")
  public ResponseEntity<List<VoteSchedule>> findAllVoteSchedules() {
    return ResponseEntity.ok().body(service.findAllVoteSchedule());
  }
}
