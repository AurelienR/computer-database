package com.excilys.cdb.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.dtos.ComputerDTO;

/**
 * Model of a Computer
 * 
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
	public Computer(int id, String name, Company company, LocalDateTime discontinued, LocalDateTime introduced) {
		this.id = id;
		this.name = name;
		this.company = company;
		this.discontinued = discontinued;
		this.introduced = introduced;
	}

	public Computer() {
	};

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
	
	public ComputerDTO toComputerDTO(){
		String introStr = ((introduced == null) ? "" : introduced.toString());
		String discStr = ((discontinued == null) ? "" : discontinued.toString());
		return new ComputerDTO(id,name,introStr,discStr,company.toCompanyDTO());
	}
	
	public static List<ComputerDTO> toComputerDTOList(List<Computer> computers){		
		List<ComputerDTO> computerDTOs = new ArrayList<ComputerDTO>();
		computers.parallelStream().forEachOrdered(c -> computerDTOs.add(c.toComputerDTO()));
		return computerDTOs;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(Computer.class.getSimpleName()).append(": ").append("\n id: ").append(Integer.toString(getId()))
				.append("\n name: ").append(getName()).append("\n introduced: ").append(getIntroduced())
				.append("\n discontinued: ").append(getDiscontinued()).append("\n\tCompany: ").append(getCompany().toString());

		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result + ((discontinued == null) ? 0 : discontinued.hashCode());
		result = prime * result + id;
		result = prime * result + ((introduced == null) ? 0 : introduced.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Computer other = (Computer) obj;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (discontinued == null) {
			if (other.discontinued != null)
				return false;
		} else if (!discontinued.equals(other.discontinued))
			return false;
		if (id != other.id)
			return false;
		if (introduced == null) {
			if (other.introduced != null)
				return false;
		} else if (!introduced.equals(other.introduced))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
