package com.excilys.cdb.validator;

public class ModelValidator {
	
	public static void checkNameNotNull(String name) throws ValidatorException {
		if(name == null) throw new ValidatorException("Company name should not be null");
	}
	
	public static void checkNameNotEmpty(String name) throws ValidatorException {
		if(name.isEmpty()) throw new ValidatorException("Company name should not be empty");
	}
	
	public static void checkValidId(int id) throws ValidatorException {		
		if(id < 0) throw new ValidatorException("Company id should be a positive integer :"+id);
	}
}
