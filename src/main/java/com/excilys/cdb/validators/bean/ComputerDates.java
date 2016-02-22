package com.excilys.cdb.validators.bean;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

//Linking the validator I had shown above.
@Constraint(validatedBy = { ComputerDatesValidator.class })
// This constraint annotation can be used only on fields and method parameters.
@Target({ ElementType.TYPE })
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface ComputerDates {

  /**
   * Message of the exception thrown.
   *
   * @return the string
   */
  // The message to return when the instance of MyAddress fails the validation.
  String message() default "Invalid introduced compare to discontinued date";

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
