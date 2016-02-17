package com.excilys.cdb.validators;

import java.time.LocalDateTime;

import com.excilys.cdb.models.Computer;

public class ComputerValidator extends ModelValidator {
	
	public static void checkDateIsNotNull(LocalDateTime date) throws ValidatorException{
		if(date == null){
			throw new ValidatorException("Date should be not null");
		} 
	}
	
	public static void checkDatesConsistence(Computer computer) throws ValidatorException{
		checkDatesConsistence(computer.getIntroduced(), computer.getDiscontinued());
	}
	
	public static void checkDatesConsistence(LocalDateTime intro, LocalDateTime disc) throws ValidatorException{
		if(disc != null && intro == null){
			throw new ValidatorException("Discontinued date should not be set if introduced date is null");
		} 
		if(disc != null && intro != null &&  disc.isBefore(intro)){
			throw new ValidatorException("Discontinued date should not be set before introduced date");
		}		
	}
	
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
