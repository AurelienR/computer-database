package com.excilys.cdb.dtos;

public class ComputerDto {

  private long id;
  private String name;
  private String introduced;
  private String discontinued;
  private CompanyDto company;

  /**
   * Instantiates a new computer dto.
   *
   * @param id
   *          the id of the computer
   * @param name
   *          the name of the computer
   * @param introduced
   *          the introduced date
   * @param discontinued
   *          the discontinued date
   * @param company
   *          the related company
   */
  public ComputerDto(long id, String name, String introduced, String discontinued,
      CompanyDto company) {
    this.id = id;
    this.name = name;
    this.introduced = introduced;
    this.discontinued = discontinued;
    this.company = company;
  }

  public ComputerDto(){
    
  }
  
  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getIntroduced() {
    return introduced;
  }

  public void setIntroduced(String introduced) {
    this.introduced = introduced;
  }

  public String getDiscontinued() {
    return discontinued;
  }

  public void setDiscontinued(String discontinued) {
    this.discontinued = discontinued;
  }

  public CompanyDto getCompany() {
    return company;
  }

  public void setCompany(CompanyDto company) {
    this.company = company;
  }

  @Override
  public String toString() {
    return "ComputerDto [id=" + id + ", name=" + name + ", introduced=" + introduced
        + ", discontinued=" + discontinued + ", company=" + company + "]";
  }
}
