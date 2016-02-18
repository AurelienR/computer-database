package com.excilys.cdb.validators;

import com.excilys.cdb.models.QueryPageParameter;

public class QueryPageParameterValidator{

	public static void checkPageIndex(int pageIndex) throws ValidatorException {
		if(pageIndex < 1){
			throw new ValidatorException("Page index cannot be less than 1");
		}
	}
	
	public static void checkPageSize(int pageSize) throws ValidatorException {
		if(pageSize < 1){
			throw new ValidatorException("Page size cannot be less than 1");
		}
	}
	
	public static void checkOffset(int offset) throws ValidatorException {
		if(offset < 0){
			throw new ValidatorException("Offset cannot be less than 0");
		}
	}
	
	public static void checkLimit(int limit) throws ValidatorException {
		if(limit < 1){
			throw new ValidatorException("Limit cannot be less than 1");
		}
	}
	
	public static void validate(QueryPageParameter qp) throws ValidatorException {
		checkPageIndex(qp.getPageIndex());
		checkPageSize(qp.getPageSize());
		checkOffset(qp.getOffset());
		checkLimit(qp.getLimit());
	}
}
