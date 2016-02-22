package com.excilys.cdb.validators;

import com.excilys.cdb.dtos.CompanyDTO;

/**
 * Validate CompanyDTO state
 * @author Aurelien.R
 *
 */
public class CompanyDTOValidator extends ModelValidator{

	/**
	 * Validate CompanyDTO fields
	 * @param companyDTO companyDTO to validate
	 * @throws ValidatorException if inconsistent fields value
	 */
	public static void validate(CompanyDTO companyDTO) throws ValidatorException {		
		CompanyDTOValidator.checkNameNotNull(companyDTO.getName());
		CompanyDTOValidator.checkNameNotEmpty(companyDTO.getName());
		CompanyDTOValidator.checkValidId(companyDTO.getId());
	}
}
