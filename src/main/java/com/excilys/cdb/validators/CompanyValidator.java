package com.excilys.cdb.validators;

import com.excilys.cdb.models.Company;

/**
 * Validate Company state
 * 
 * @author Aurelien.R
 *
 */
public class CompanyValidator extends ModelValidator {
  /**
   * Validate Company fields.
   * 
   * @param company
   *          company to validate
   * @throws ValidatorException
   *           if inconsistent fields value
   */
  public static void validate(Company company) throws ValidatorException {
    CompanyValidator.checkNameNotEmpty(company.getName());
    CompanyValidator.checkNameNotNull(company.getName());
    CompanyValidator.checkValidId(company.getId());
  }
}
