package com.backendtest.voteapi.shared.validation;

import com.backendtest.voteapi.schedule.vote.VoteOption;
import com.backendtest.voteapi.shared.exception.BadRequestException;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckVoteValidator implements ConstraintValidator<CheckVote, VoteOption> {

  @Override
  public void initialize(CheckVote dateConstraint) {}

  @Override
  public boolean isValid(VoteOption dateField, ConstraintValidatorContext cxt) {
    if (!VoteOption.NO.equals(dateField) || !VoteOption.YES.equals(dateField)) {
      throw new BadRequestException("Invalid Vote. Valid Values Are \"YES\" or \"NO\"");
    }
    return true;
  }
}
