package com.excilys.cdb.validators;

import java.time.LocalDateTime;

import com.excilys.cdb.models.Computer;

/**
 * Validate Computer state
 * @author Aurelien.R
 *
 */
public class ComputerValidator extends ModelValidator {
	
	/**
	 * Check passed date is not null
	 * @param date date to check
	 * @throws ValidatorException if date is null
	 */
	public static void checkDateIsNotNull(LocalDateTime date) throws ValidatorException{
		if(date == null){
			throw new ValidatorException("Date should be not null");
		} 
	}
	
	/**
	 * Check that computer dates are consitents
	 * @param computer computer to check
	 * @throws ValidatorException if dates are not consistent
	 */
	public static void checkDatesConsistence(Computer computer) throws ValidatorException{
		checkDatesConsistence(computer.getIntroduced(), computer.getDiscontinued());
	}
	
	/**
	 * Check if intro and discontinued dates are consistent or not
	 * @param intro intro date to check
	 * @param disc discontinued date to check
	 * @throws ValidatorException if inconsitent dates are detected
	 */
	public static void checkDatesConsistence(LocalDateTime intro, LocalDateTime disc) throws ValidatorException{
		if(disc != null && intro == null){
			throw new ValidatorException("Discontinued date should not be set if introduced date is null");
		} 
		if(disc != null && intro != null &&  disc.isBefore(intro)){
			throw new ValidatorException("Discontinued date should not be set before introduced date");
		}		
	}
	
	/**
	 * Validate all computer fields
	 * @param computer computer to validate
	 * @throws ValidatorException if computer has an invalid state
	 */
	public static void validate(Computer computer) throws ValidatorException{
		// Validate computer
		ComputerValidator.checkValidId(computer.getId());
		ComputerValidator.checkNameNotNull(computer.getName());
		ComputerValidator.checkNameNotEmpty(computer.getName());
		// Check related company
		if(computer.getCompany() != null){
			CompanyValidator.checkValidId(computer.getCompany().getId());
		}
		
	}
}
