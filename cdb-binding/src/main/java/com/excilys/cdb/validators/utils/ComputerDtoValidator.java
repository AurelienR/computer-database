package com.excilys.cdb.validators.utils;

import com.excilys.cdb.dtos.ComputerDto;
import com.excilys.cdb.utils.DateFormatManager;
import com.excilys.cdb.validators.ValidatorException;

import java.time.LocalDateTime;

/**
 * Validate ComputerDTO state
 * 
 * @author Aurelien.R
 *
 */
public class ComputerDtoValidator extends ModelValidator {

  /**
   * Checknot null date field.
   * 
   * @param date date to validate
   * @throws ValidatorException issue with data
   */
  public static void checkDateIsNotNull(String date) throws ValidatorException {
    if (date == null) {
      throw new ValidatorException("Date string should be not null");
    }
  }

  /**
   * Check not empty date field.
   * 
   * @param date date to check
   * @throws ValidatorException issue with data
   */
  public static void checkDateIsNotEmpty(String date) throws ValidatorException {
    if (date.isEmpty()) {
      throw new ValidatorException("Date string should be not empty");
    }
  }

  /**
   * Check dates consistency (introduced and discontinued).
   * 
   * @param computerDto computerDTO to check
   * @throws ValidatorException if issues on date consistencies
   */
  public static void checkDateConsistency(ComputerDto computerDto) throws ValidatorException {
    checkDateConsistency(computerDto.getIntroduced(), computerDto.getDiscontinued());
  }

  /**
   * Check dates consistency (introduced and discontinued).
   * 
   * @param intro introduced date
   * @param disc discontinued date
   * @throws ValidatorException if issues on date consistencies
   */
  public static void checkDateConsistency(String intro, String disc) throws ValidatorException {
    if ((intro == null || intro.isEmpty()) && (disc != null && !disc.isEmpty())) {
      throw new ValidatorException(
          "Introduced string should be not null/empty if discontinued string is not null/empty");
    } else if ((intro != null && !intro.isEmpty()) && (disc != null && !disc.isEmpty())
        && (DateFormatManager.isValidLocalDateString(intro)
            && DateFormatManager.isValidLocalDateString(disc))) {

      LocalDateTime introDate = DateFormatManager.parseLocal(intro);
      LocalDateTime discDate = DateFormatManager.parseLocal(disc);

      if (discDate.isBefore(introDate)) {
        throw new ValidatorException("Introduced date should be set befored discontinued date");
      }
    }
  }

  /**
   * Check date string is matchin HTML spec format.
   * 
   * @param dateStr date string to check
   * @throws ValidatorException if date string is not matching HTML format
   */
  public static void checkIsValidDateFormat(String dateStr) throws ValidatorException {
    if (!DateFormatManager.isValidLocalDateString(dateStr)) {
      throw new ValidatorException("Date " + dateStr + " is not matching date format "
          + DateFormatManager.getLocalDateFormat());
    }
  }

  /**
   * Check date HTML format if date string is not null.
   * 
   * @param dateStr date string to check
   * @throws ValidatorException if date string is not null or not matching HTML format
   */
  public static void checkDateFormatIfNotNull(String dateStr) throws ValidatorException {
    if (dateStr != null && !dateStr.isEmpty()) {
      if (!DateFormatManager.isValidLocalDateString(dateStr)) {
        throw new ValidatorException("Date " + dateStr + " is not matching date format "
            + DateFormatManager.getLocalDateFormat());
      }
    }
  }

  /**
   * Check introduced and discontinued dates formats.
   * 
   * @param computerDto computerDTO to check dates from
   * @throws ValidatorException if computerDTO date is not HTML formated
   */
  public static void checkDateFormatIfNotNull(ComputerDto computerDto) throws ValidatorException {
    checkDateFormatIfNotNull(computerDto.getIntroduced());
    checkDateFormatIfNotNull(computerDto.getDiscontinued());
  }

  /**
   * Validate all ComputerDTO fields.
   * 
   * @param computerDto computerDTO to validate
   * @throws ValidatorException if computerDTO is not valid
   */
  public static void validate(ComputerDto computerDto) throws ValidatorException {

    // Validate computerDTO
    ComputerDtoValidator.checkValidId(computerDto.getId());
    ComputerDtoValidator.checkNameNotNull(computerDto.getName());
    ComputerDtoValidator.checkNameNotEmpty(computerDto.getName());
    checkDateConsistency(computerDto);
    checkDateFormatIfNotNull(computerDto);

    // Validate related companyDTO
    if (computerDto.getCompany() != null) {
      CompanyDtoValidator.checkValidId(computerDto.getCompany().getId());
    }
  }
}
