package com.excilys.cdb.validators;

import com.excilys.cdb.models.Computer;

import java.time.LocalDateTime;

// TODO: Auto-generated Javadoc
/**
 * Validate Computer state.
 *
 * @author Aurelien.R
 */
public class ComputerValidator extends ModelValidator {

  /**
   * Check passed date is not null.
   *
   * @param date          date to check
   * @throws ValidatorException           if date is null
   */
  public static void checkDateIsNotNull(LocalDateTime date) throws ValidatorException {
    if (date == null) {
      throw new ValidatorException("Date should be not null");
    }
  }

  /**
   * Check that computer dates are consitents.
   *
   * @param computer          computer to check
   * @throws ValidatorException           if dates are not consistent
   */
  public static void checkDatesConsistence(Computer computer) throws ValidatorException {
    checkDatesConsistence(computer.getIntroduced(), computer.getDiscontinued());
  }

  /**
   * Check if intro and discontinued dates are consistent or not.
   *
   * @param intro          intro date to check
   * @param disc          discontinued date to check
   * @throws ValidatorException           if inconsitent dates are detected
   */
  public static void checkDatesConsistence(LocalDateTime intro, LocalDateTime disc)
      throws ValidatorException {
    if (disc != null && intro == null) {
      throw new ValidatorException(
          "Discontinued date should not be set if introduced date is null");
    }
    if (disc != null && intro != null && disc.isBefore(intro)) {
      throw new ValidatorException("Discontinued date should not be set before introduced date");
    }
  }

  /**
   * Check starting row.
   *
   * @param startingRow          starting row to check
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
   * @param size          size to check
   * @throws ValidatorException           if size is < 0
   */
  public static void checkSize(int size) throws ValidatorException {
    if (size < 0) {
      throw new ValidatorException("Size of range searched cannot be < 0, size: " + size);
    }
  }

  /**
   * Validate all computer fields.
   *
   * @param computer          computer to validate
   * @throws ValidatorException           if computer has an invalid state
   */
  public static void validate(Computer computer) throws ValidatorException {
    // Validate computer
    ComputerValidator.checkValidId(computer.getId());
    ComputerValidator.checkNameNotNull(computer.getName());
    ComputerValidator.checkNameNotEmpty(computer.getName());
    // Check related company
    if (computer.getCompany() != null) {
      CompanyValidator.checkValidId(computer.getCompany().getId());
    }

  }
}
