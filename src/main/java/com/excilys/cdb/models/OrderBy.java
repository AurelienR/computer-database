package com.excilys.cdb.models;

public enum OrderBy {
	
	id("computer.id"),
	name("computer.name"),
	introduced("computer.introduced"),
	discontinued("computer.discontinued"),
	company("company.name");
	
	private final String text;
	
	private OrderBy(String text){
		this.text = text;
	}
	
	@Override
	public String toString(){
		return this.text;
	}
}
