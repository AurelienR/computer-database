package com.excilys.cdb.validators.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = { ComputerDtoDateFormatValidator.class })
@Target({ ElementType.FIELD})
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface ComputerDtoDateFormat {

  /**
   * Message of the exception thrown.
   *
   * @return the string
   */
  String message() default "Invalid date string format";

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