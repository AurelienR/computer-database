package models;

import java.sql.Timestamp;

public class Computer {
	private int id = 0;
	private Company company;
	private String name;
	private Timestamp discontinued;
	private Timestamp introduced;
	
	public Computer(int id, String name,Company company, Timestamp discontinued, Timestamp introduced){
		setId(id);
		setName(name);
		setCompany(company);
		setDiscontinued(discontinued);
		setIntroduced(introduced);
	}
	
	public Computer(){};

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
		if(introduced != null && introduced.after(discontinued)){
			throw new IllegalArgumentException("Setting discontinued attribute before introduced timestamp");
		}
		this.discontinued = discontinued;
	}

	public Timestamp getIntroduced() {
		return introduced;
	}

	public void setIntroduced(Timestamp introduced) throws IllegalArgumentException {
		if(discontinued != null && discontinued.before(introduced)){
			throw new IllegalArgumentException("Setting introduced attribute after discontinued timestamp");
		}
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
