package com.excilys.cdb.models;

import java.time.LocalDateTime;
import java.util.Objects;

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
	private LocalDateTime discontinued;
	private LocalDateTime introduced;
	
	// Constructors
	public Computer(int id, String name,Company company, LocalDateTime discontinued, LocalDateTime introduced){
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

	public LocalDateTime getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(LocalDateTime discontinued) {
		this.discontinued = discontinued;
	}

	public LocalDateTime getIntroduced() {
		return introduced;
	}

	public void setIntroduced(LocalDateTime introduced) {
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
	
	@Override
	public int hashCode() {
		return Objects.hash(this.getId(),this.getName());
	}

	@Override
	public boolean equals(Object obj) {
		// Check type
	    if (obj == null) return false;
	    if (obj == this) return true;
	    if (!(obj instanceof Computer))return false;
	    
	    Computer companyObj = (Computer)obj;	    
	    // Check attributes
	    // Check null
	    if(Objects.isNull(companyObj.getName()) && !Objects.isNull(this.getName())) return false;
	    if(Objects.isNull(this.getName()) && !Objects.isNull(companyObj.getName())) return false;
	    if(Objects.isNull(companyObj.getIntroduced()) && !Objects.isNull(this.getIntroduced())) return false;
	    if(Objects.isNull(this.getIntroduced()) && !Objects.isNull(companyObj.getIntroduced())) return false;
	    if(Objects.isNull(companyObj.getDiscontinued()) && !Objects.isNull(this.getDiscontinued())) return false;
	    if(Objects.isNull(this.getDiscontinued()) && !Objects.isNull(companyObj.getDiscontinued())) return false;
	    if(Objects.isNull(companyObj.getCompany()) && !Objects.isNull(this.getCompany())) return false;
	    if(Objects.isNull(this.getCompany()) && !Objects.isNull(companyObj.getCompany())) return false;
	    // Check values
	    if(this.getId() != companyObj.getId()) return false;
	    if(!this.getName().equals(companyObj.getName())) return false;
	    if(!this.getIntroduced().equals(companyObj.getIntroduced())) return false;
	    if(!this.getDiscontinued().equals(companyObj.getDiscontinued())) return false;
	    if(!this.getCompany().equals(companyObj.getCompany())) return false;
	    
	    return true;
	}
	
}
