package com.backendtest.voteapi.schedule.vote;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Api(tags = "Votes")
@RestController
@AllArgsConstructor
public class VoteController {

    private VoteService service;

    @ApiOperation(value = "Submit Vote")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 422, message = "UNPROCESSABLE_ENTITY (This is related to business rules)"),})
    @PostMapping(value = "/votes")
    public ResponseEntity<Vote> submitVote(@ApiParam(value = "Vote Object.\n - voteOption: are \"YES\" or \"NO\"", required = true)
                                           @Valid @RequestBody VoteDto voteDTO) {
        Vote vote = service.submitVote(voteDTO);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(vote.getVoteId()).toUri()).body(vote);
    }

}
