package com.excilys.cdb.validators.bean;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.excilys.cdb.models.Computer;

public class ComputerDatesValidator implements ConstraintValidator<ComputerDates, Computer> {

	@Override
	public void initialize(ComputerDates constraintAnnotation) {
	}

	@Override
	public boolean isValid(Computer value, ConstraintValidatorContext context) {
		
		// Introduced should be not null if discontinued is not null
		if(value.getDiscontinued()!= null && value.getIntroduced() == null){
			return false;
		}
		// Discontinued should not be set after discontinued
		if(value.getDiscontinued()!= null && value.getIntroduced() != null){
			if(value.getDiscontinued().isBefore(value.getIntroduced())){
				return false;
			}
		}
	    return true;
	}
}