package com.backendtest.voteapi.shared.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = CheckVoteValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckVote {
  String message() default "Invalid Vote. Valid Values Are \"YES\" or \"NO\"";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
