package com.excilys.cdb.validators;

import com.excilys.cdb.dtos.CompanyDTO;

public class CompanyDTOValidator extends ModelValidator{

	public static void validate(CompanyDTO companyDTO) throws ValidatorException {		
		CompanyDTOValidator.checkNameNotNull(companyDTO.getName());
		CompanyDTOValidator.checkNameNotEmpty(companyDTO.getName());
		CompanyDTOValidator.checkValidId(companyDTO.getId());
	}
}
