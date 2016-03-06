package com.excilys.cdb.validators.spring;

import javax.validation.ValidationException;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.excilys.cdb.dtos.CompanyDto;
import com.excilys.cdb.validators.CompanyDtoValidator;

@Component
public class SpringCompanyDtoValidator implements Validator {

    @Override
    public boolean supports(Class<?> classToTest) {
	return CompanyDto.class.equals(classToTest);
    }

    @Override
    public void validate(Object target, Errors errors) {

	CompanyDto companyDto = (CompanyDto) target;

	try {
	    CompanyDtoValidator.checkNameNotNull(companyDto.getName());
	} catch (ValidationException e) {
	    errors.rejectValue("name", "name.required", "Name field is missing");
	}

	try {
	    CompanyDtoValidator.checkNameNotEmpty(companyDto.getName());
	} catch (ValidationException e) {
	    errors.rejectValue("name", "name.required", "Name field should not be empty");
	}

	try {
	    CompanyDtoValidator.checkValidId(companyDto.getId());
	} catch (ValidationException e) {
	    errors.rejectValue("id", "id.invalid", "Id should not be negative");
	}

    }

}
