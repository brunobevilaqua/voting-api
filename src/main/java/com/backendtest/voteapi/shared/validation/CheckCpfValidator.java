package com.backendtest.voteapi.shared.validation;

import com.backendtest.voteapi.associate.AssociateService;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CheckCpfValidator implements ConstraintValidator<CheckCpf, String> {

  @Autowired
  private AssociateService associateService;

  @Override
  public void initialize(CheckCpf constraintAnnotation) {

  }

  @Override
  public boolean isValid(String cpf, ConstraintValidatorContext context) {
    return associateService.isAssociateAbleToVote(cpf);
  }

}
