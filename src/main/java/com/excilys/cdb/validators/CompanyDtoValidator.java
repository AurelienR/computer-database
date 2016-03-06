package com.excilys.cdb.validators;

import com.excilys.cdb.dtos.CompanyDto;

/**
 * Validate CompanyDTO state.
 *
 * @author Aurelien.R
 */
public class CompanyDtoValidator extends ModelValidator {

  /**
   * Validate CompanyDTO fields.
   *
   * @param companyDto          companyDTO to validate
   * @throws ValidatorException           if inconsistent fields value
   */
  public static void validate(CompanyDto companyDto) throws ValidatorException {    
    CompanyDtoValidator.checkNameNotNull(companyDto.getName());
    CompanyDtoValidator.checkNameNotEmpty(companyDto.getName());
    CompanyDtoValidator.checkValidId(companyDto.getId());
  }
}
