package com.excilys.cdb.validators.spring;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.excilys.cdb.dtos.ComputerDto;
import com.excilys.cdb.validators.CompanyDtoValidator;
import com.excilys.cdb.validators.ComputerDtoValidator;
import com.excilys.cdb.validators.ValidatorException;

@Component
public class SpringComputerDtoValidator implements Validator {

  @Override
  public boolean supports(Class<?> classToTest) {
    return ComputerDto.class.equals(classToTest);
  }

  @Override
  public void validate(Object target, Errors errors) {

    ComputerDto computerDto = (ComputerDto) target;

    // Validate computerDTO
    try {
      ComputerDtoValidator.checkValidId(computerDto.getId());
    } catch (ValidatorException e) {
      errors.rejectValue("id", "id.invalid", "Id should not be nagative");
    }

    try {
      ComputerDtoValidator.checkNameNotNull(computerDto.getName());
    } catch (ValidatorException e) {
      errors.rejectValue("name", "name.required", "Name field is missing");
    }

    try {
      ComputerDtoValidator.checkNameNotEmpty(computerDto.getName());
    } catch (ValidatorException e) {
      errors.rejectValue("name", "name.required", "Name field should not be empty");
    }

    try {
      ComputerDtoValidator.checkDateFormatIfNotNull(computerDto);
    } catch (ValidatorException e) {
      errors.rejectValue("date", "date.invalid", "Date format is not valid");
    }

    try {
      ComputerDtoValidator.checkDateConsistency(computerDto);
    } catch (ValidatorException e) {
      errors.rejectValue("date", "date.inconsistent", "Date are inconsistent");
    }

    // Validate related companyDTO
    if (computerDto.getCompany() != null) {
      try {
        CompanyDtoValidator.checkValidId(computerDto.getCompany().getId());
      } catch (ValidatorException e) {
        errors.rejectValue("companyId", "companyId.invalid", "CompanyId should not be negative");
      }

    }

  }
}
