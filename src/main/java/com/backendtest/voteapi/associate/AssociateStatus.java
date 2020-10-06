package com.backendtest.voteapi.associate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AssociateStatus {
    ABLE_TO_VOTE,
    UNABLE_TO_VOTE;
}
