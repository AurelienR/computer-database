package com.excilys.cdb.utils;

public class QueryBuilder {
	
	private StringBuilder querySb;
	
	public QueryBuilder(){
		querySb = new StringBuilder();
	}
	
	public QueryBuilder select(String selector){
		 querySb.append("SELECT")
				.append(" ")
				.append(selector)
				.append(" ");
		 return this;
	}
	
	public QueryBuilder from(String selector){
		 querySb.append("FROM")
				.append(" ")
				.append(selector)
				.append(" ");
		 return this;
	}
	
	public QueryBuilder where(String constraint){
		 querySb.append("WHERE")
			.append(" ")
			.append(constraint)
			.append(" ");
		 return this;
	}
	
	public QueryBuilder offset(int offset){
		 querySb.append("OFFSET")
			.append(" ")
			.append(offset)
			.append(" ");
		 return this;
	}
	
	public QueryBuilder orderBy(String constraint){
		 querySb.append("ORDER BY")
			.append(" ")
			.append(constraint)
			.append(" ");
		 return this;
	}
	
	public QueryBuilder limit(int offset){
		 querySb.append("LIMIT")
			.append(" ")
			.append(offset)
			.append(" ");
		 return this;
	}
	
	public String toQuery(){
		return querySb.toString();
	}
}
