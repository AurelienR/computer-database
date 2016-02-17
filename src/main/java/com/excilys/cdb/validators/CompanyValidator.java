package com.excilys.cdb.validators;

import com.excilys.cdb.models.Company;

public class CompanyValidator extends ModelValidator {
	public static void validate(Company company) throws ValidatorException{		
		CompanyValidator.checkNameNotEmpty(company.getName());
		CompanyValidator.checkNameNotNull(company.getName());
		CompanyValidator.checkValidId(company.getId());
	}
}
