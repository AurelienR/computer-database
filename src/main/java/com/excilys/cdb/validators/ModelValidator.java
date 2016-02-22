package com.excilys.cdb.validators;

// TODO: Auto-generated Javadoc
/**
 * Basic Model validation.
 *
 * @author Aurelien.R
 */
public class ModelValidator {

  /**
   * Check whether the passed string name is not null.
   *
   * @param name          name to check
   * @throws ValidatorException           if name string is null
   */
  public static void checkNameNotNull(String name) throws ValidatorException {
    if (name == null) {
      throw new ValidatorException("Company name should not be null");
    }
  }

  /**
   * Check whetehr a Name string is not empty.
   *
   * @param name          name to validate
   * @throws ValidatorException           if name string is empty
   */
  public static void checkNameNotEmpty(String name) throws ValidatorException {
    if (name.isEmpty()) {
      throw new ValidatorException("Company name should not be empty");
    }
  }

  /**
   * Check if passed id is valid (not < 0).
   *
   * @param id          id to check
   * @throws ValidatorException           if id is not valid
   */
  public static void checkValidId(int id) throws ValidatorException {
    if (id < 0) {
      throw new ValidatorException("Company id should be a positive integer :" + id);
    }
  }
}
