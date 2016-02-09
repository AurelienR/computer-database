package com.excilys.cdb.models;

import java.util.Objects;

/**
 * Model of a company
 * @author Aurelien.R
 *
 */
public class Company {
	private int id;
	private String name;
	
	public Company(){};
	
	public Company(int id, String name){
		this.id =  id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(Company.class.getSimpleName())
		.append(" :")
		.append("id: ").append(Integer.toString(getId()))
		.append(" name:").append(getName());
		
		return sb.toString();
	}


	@Override
	public int hashCode() {
		return Objects.hash(this.getId(), this.getName());
	}

	@Override
	public boolean equals(Object obj) {
		// Check type
	    if (obj == null) return false;
	    if (obj == this) return true;
	    if (!(obj instanceof Company))return false;
	    
	    Company companyObj = (Company)obj;	    
	    // Check attributes
	    // Check null 
	    if(Objects.isNull(companyObj.getName()) && !Objects.isNull(this.getName())) return false;
	    if(Objects.isNull(this.getName()) && !Objects.isNull(companyObj.getName())) return false;
	    // Check values
	    if(this.getId() != companyObj.getId()) return false;
	    if(!this.getName().equals(companyObj.getName())) return false;
	    
	    return true;
	}
	
	
}
