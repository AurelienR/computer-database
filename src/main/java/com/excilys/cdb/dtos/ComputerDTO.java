package com.excilys.cdb.dtos;

public class ComputerDTO {
		
	public int id;
	public String name;
	public String introduced;
	public String discontinued;
	public CompanyDTO company;
	
	public ComputerDTO(int id , String name, String introduced, String discontinued, CompanyDTO company){
		this.id = id;
		this.name = name;
		this.introduced =introduced;
		this.discontinued = discontinued;
		this.company = company;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the introduced
	 */
	public String getIntroduced() {
		return introduced;
	}

	/**
	 * @param introduced the introduced to set
	 */
	public void setIntroduced(String introduced) {
		this.introduced = introduced;
	}

	/**
	 * @return the discontinued
	 */
	public String getDiscontinued() {
		return discontinued;
	}

	/**
	 * @param discontinued the discontinued to set
	 */
	public void setDiscontinued(String discontinued) {
		this.discontinued = discontinued;
	}

	/**
	 * @return the company
	 */
	public CompanyDTO getCompany() {
		return company;
	}

	/**
	 * @param company the company to set
	 */
	public void setCompany(CompanyDTO company) {
		this.company = company;
	}

}
