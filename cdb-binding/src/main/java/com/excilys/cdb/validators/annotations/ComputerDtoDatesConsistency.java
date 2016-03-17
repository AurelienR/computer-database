package com.excilys.cdb.validators.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = { ComputerDtoDatesConsistencyValidator.class })
@Target({ ElementType.TYPE })
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface ComputerDtoDatesConsistency {

  /**
   * Message of the exception thrown.
   *
   * @return the string
   */
  String message() default "Inconsistent dates, "
      + "introduced date cannot be set after discontinued date";

  /**
   * Groups.
   *
   * @return the class[]
   */
  Class<?>[] groups() default {};

  /**
   * Payload.
   *
   * @return the class<? extends payload>[]
   */
  Class<? extends Payload>[] payload() default {};
}