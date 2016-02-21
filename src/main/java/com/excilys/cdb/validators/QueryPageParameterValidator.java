package com.excilys.cdb.validators;

import com.excilys.cdb.models.Order;
import com.excilys.cdb.models.OrderBy;
import com.excilys.cdb.models.QueryPageParameter;

public class QueryPageParameterValidator{

	public static void checkPageIndex(int pageIndex) throws ValidatorException {
		if(pageIndex < 1){
			throw new ValidatorException("Page index cannot be less than 1, pageIndex: "+ pageIndex );
		}
	}
	
	public static void checkPageSize(int pageSize) throws ValidatorException {
		if(pageSize < 1){
			throw new ValidatorException("Page size cannot be less than 1, pageSize : "+ pageSize);
		}
	}
	
	public static void checkOffset(int offset) throws ValidatorException {
		if(offset < 0){
			throw new ValidatorException("Offset cannot be less than 0, offset: " + offset);
		}
	}
	
	public static void checkLimit(int limit) throws ValidatorException {
		if(limit < 1){
			throw new ValidatorException("Limit cannot be less than 1, limit : "+limit);
		}
	}
	
	public static void checkSearch(String search){
		if(search == null){
			throw new ValidatorException("Search field string cannot be null");
		}
	}
	
	public static void checkOrderBy(OrderBy orderBy){
		if(orderBy == null){
			throw new ValidatorException("OrderBy enum field cannot be null");
		}
	}
	
	public static void checkOrder(Order order){
		if(order == null){
			throw new ValidatorException("Order enum field cannot be null");
		}
	}
	
	public static void checkMatchingRowCount(int count){
		if(count < 0){
			throw new ValidatorException("MatchingRowCount cannot be a negative number: "+ count);
		}
	}
	
	public static void validate(QueryPageParameter qp) throws ValidatorException {
		checkPageIndex(qp.getPageIndex());
		checkPageSize(qp.getPageSize());
		checkOffset(qp.getOffset());
		checkLimit(qp.getLimit());
		checkSearch(qp.getSearch());
		checkOrderBy(qp.getOrderBy());
		checkOrder(qp.getOrder());
		checkMatchingRowCount(qp.getMatchingRowCount());
	}
}
