package com.excilys.cdb.validators;

import com.excilys.cdb.dtos.ComputerDTO;
import com.excilys.cdb.utils.DateFormatManager;

public class ComputerDTOValidator extends ModelValidator{
	
	public static void checkDateIsNotNull(String date) throws ValidatorException {
		if(date == null){
			throw new ValidatorException("Date string should be not null");
		} 
	}
	
	public static void checkDateIsNotEmpty(String date) throws ValidatorException {
		if(date.isEmpty()){
			throw new ValidatorException("Date string should be not empty");
		} 
	}
	
	public static void checkDateConsistency(ComputerDTO computerDTO) throws ValidatorException {
		checkDateConsistency(computerDTO.getIntroduced(), computerDTO.getDiscontinued());
	}
	
	public static void checkDateConsistency(String intro, String disc) throws ValidatorException {
		if((intro == null || intro.isEmpty()) && (disc != null && !disc.isEmpty())){
			throw new ValidatorException("Introduced string should be not null/empty if discontinued string is not null/empty");
		}
	}
	
	public static void checkIsValidDateFormat(String dateStr) throws ValidatorException {
		if(!DateFormatManager.isValidHTMLStringFormat(dateStr)){
			throw new ValidatorException("Date "+dateStr+" is not matching date format " + DateFormatManager.HTML_DATE_FORMAT);
		}
	}
}
