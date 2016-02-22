package com.excilys.cdb.validators;

import com.excilys.cdb.dtos.ComputerDTO;
import com.excilys.cdb.utils.DateFormatManager;

/**
 * Validate ComputerDTO state
 * 
 * @author Aurelien.R
 *
 */
public class ComputerDTOValidator extends ModelValidator{
	
	/**
	 * Checknot null date field
	 * @param date date to validate
	 * @throws ValidatorException
	 */
	public static void checkDateIsNotNull(String date) throws ValidatorException {
		if(date == null){
			throw new ValidatorException("Date string should be not null");
		} 
	}
	
	/**
	 * Check not empty date field
	 * @param date
	 * @throws ValidatorException
	 */
	public static void checkDateIsNotEmpty(String date) throws ValidatorException {
		if(date.isEmpty()){
			throw new ValidatorException("Date string should be not empty");
		} 
	}
	
	/**
	 * Check dates consistency (introduced and discontinued)
	 * @param computerDTO computerDTO to check 
	 * @throws ValidatorException if issues on date consistencies
	 */
	public static void checkDateConsistency(ComputerDTO computerDTO) throws ValidatorException {
		checkDateConsistency(computerDTO.getIntroduced(), computerDTO.getDiscontinued());
	}
	
	/**
	 * Check dates consistency (introduced and discontinued)
	 * @param intro introduced date
	 * @param disc discontinued date
	 * @throws ValidatorException if issues on date consistencies
	 */
	public static void checkDateConsistency(String intro, String disc) throws ValidatorException {
		if((intro == null || intro.isEmpty()) && (disc != null && !disc.isEmpty())){
			throw new ValidatorException("Introduced string should be not null/empty if discontinued string is not null/empty");
		}
	}
	
	/**
	 * Check date string is matchin HTML spec format
	 * @param dateStr date string to check
	 * @throws ValidatorException if date string is not matching HTML format
	 */
	public static void checkIsValidDateFormat(String dateStr) throws ValidatorException {
		if(!DateFormatManager.isValidHTMLStringFormat(dateStr)){
			throw new ValidatorException("Date "+dateStr+" is not matching date format " + DateFormatManager.HTML_DATE_FORMAT);
		}
	}
	
	/**
	 * Check date HTML format if date string is not null
	 * @param dateStr date string to check
	 * @throws ValidatorException if date string is not null or not matching HTML format
	 */
	public static void checkDateFormatIfNotNull(String dateStr) throws ValidatorException {
		if(dateStr != null && !dateStr.isEmpty()){
			if(!DateFormatManager.isValidHTMLStringFormat(dateStr)){
				throw new ValidatorException("Date "+dateStr+" is not matching date format " + DateFormatManager.HTML_DATE_FORMAT);
			}
		}
	}
	
	/**
	 * Check introduced and discontinued dates formats
	 * @param computerDTO computerDTO to check dates from
	 * @throws ValidatorException if computerDTO date is not HTML formated
	 */
	public static void checkDateFormatIfNotNull(ComputerDTO computerDTO) throws ValidatorException {
		checkDateFormatIfNotNull(computerDTO.getIntroduced());
		checkDateFormatIfNotNull(computerDTO.getDiscontinued());
	}
	
	/**
	 * Validate all ComputerDTO fields
	 * @param computerDTO computerDTO to validate
	 * @throws ValidatorException if computerDTO is not valid
	 */
	public static void validate(ComputerDTO computerDTO) throws ValidatorException {
		
		// Validate computerDTO
		ComputerDTOValidator.checkValidId(computerDTO.getId());
		ComputerDTOValidator.checkNameNotNull(computerDTO.getName());
		ComputerDTOValidator.checkNameNotEmpty(computerDTO.getName());
		ComputerDTOValidator.checkDateConsistency(computerDTO);
		ComputerDTOValidator.checkDateFormatIfNotNull(computerDTO);
		
		// Validate related companyDTO
		if(computerDTO.getCompany() != null){
			CompanyDTOValidator.checkValidId(computerDTO.getCompany().getId());
		}
	}
}
