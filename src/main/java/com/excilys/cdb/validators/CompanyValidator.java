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
   * @param company company to validate
   * @throws ValidatorException if inconsistent fields value
   */
  public static void validate(Company company) throws ValidatorException {
    CompanyValidator.checkNameNotEmpty(company.getName());
    CompanyValidator.checkNameNotNull(company.getName());
    CompanyValidator.checkValidId(company.getId());
  }

  /**
   * Check starting row.
   *
   * @param startingRow starting row to check
   * @throws ValidatorException the validator exception
   */
  public static void checkStartingRow(int startingRow) throws ValidatorException {
    if (startingRow < 0) {
      throw new ValidatorException(
          "Starting row of range cannot be < 0, startingrow: " + startingRow);
    }
  }

  /**
   * Check size parameter.
   *
   * @param size size to check
   * @throws ValidatorException if size is < 0
   */
  public static void checkSize(int size) throws ValidatorException {
    if (size < 0) {
      throw new ValidatorException("Size of range searched cannot be < 0, size: " + size);
    }
  }
}
