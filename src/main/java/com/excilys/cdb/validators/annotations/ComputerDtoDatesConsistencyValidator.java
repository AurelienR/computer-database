package com.excilys.cdb.validators.annotations;

import com.excilys.cdb.dtos.ComputerDto;
import com.excilys.cdb.validators.ValidatorException;
import com.excilys.cdb.validators.utils.ComputerDtoValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ComputerDtoDatesConsistencyValidator
    implements ConstraintValidator<ComputerDtoDatesConsistency, ComputerDto> {

  @Override
  public void initialize(ComputerDtoDatesConsistency constraintAnnotation) {
  }

  @Override
  public boolean isValid(ComputerDto value, ConstraintValidatorContext context) {
    try {
      ComputerDtoValidator.checkDateConsistency(value);
    } catch (ValidatorException e) {
      return false;
    }
    return true;
  }

}