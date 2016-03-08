package com.excilys.cdb.validators.annotations;

import com.excilys.cdb.validators.ValidatorException;
import com.excilys.cdb.validators.utils.ComputerDtoValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ComputerDtoDateFormatValidator
    implements ConstraintValidator<ComputerDtoDateFormat, String> {

  @Override
  public void initialize(ComputerDtoDateFormat constraintAnnotation) {

  }

  @Override
  public boolean isValid(String date, ConstraintValidatorContext context) {
    
    try {
      ComputerDtoValidator.checkDateFormatIfNotNull(date);
    } catch (ValidatorException e) {
      return false;
    }
    return true;
  }
}
