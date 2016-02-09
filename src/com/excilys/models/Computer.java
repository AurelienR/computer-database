package com.excilys.models;

import java.sql.Timestamp;

/**
 * Model of a Computer
 * @author Aurelien.R
 *
 */
public class Computer {
	// Attributes
	private int id = 0;
	private Company company;
	private String name;
	private Timestamp discontinued;
	private Timestamp introduced;
	
	// Constructors
	public Computer(int id, String name,Company company, Timestamp discontinued, Timestamp introduced){
		this.id = id;
		this.name = name;
		this.company = company;
		this.discontinued = discontinued;
		this.introduced = introduced;
	}	
	public Computer(){};

	// Getter and Setter
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Timestamp getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(Timestamp discontinued) throws IllegalArgumentException {
		this.discontinued = discontinued;
	}

	public Timestamp getIntroduced() {
		return introduced;
	}

	public void setIntroduced(Timestamp introduced) throws IllegalArgumentException {
		this.introduced = introduced;
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(Computer.class.getSimpleName())
		.append(" :")
		.append("id: ").append(Integer.toString(getId()))
		.append(" name:").append(getName())
		.append(" introduced:").append(getIntroduced())
		.append(" discontinued:").append(getDiscontinued())
		.append(" Company:").append(getCompany().toString());
		
		return sb.toString();
	}
	
}
