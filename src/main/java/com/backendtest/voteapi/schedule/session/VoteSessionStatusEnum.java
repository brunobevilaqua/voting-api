package com.backendtest.voteapi.schedule.session;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum VoteSessionStatusEnum {
    OPEN("OPEN"),
    CLOSED("CLOSED");
    private String message;
}
