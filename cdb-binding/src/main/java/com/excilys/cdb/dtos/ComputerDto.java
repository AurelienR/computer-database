package com.excilys.cdb.dtos;

import com.excilys.cdb.validators.annotations.ComputerDtoDateFormat;
import com.excilys.cdb.validators.annotations.ComputerDtoDatesConsistency;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ComputerDtoDatesConsistency(message = "Invalid date consistency")
public class ComputerDto {

  @Min(value = 0, message = "Computer id cannot be negative number {value}")
  private long id;
  @NotNull(message = "Computer name should be not null")
  @Size(min = 0, message = "Computer name cannot be empty string")
  private String name;
  @ComputerDtoDateFormat(message = "Invalid format for introduced date")
  private String introduced;
  @ComputerDtoDateFormat(message = "Invalid format for discontinued date")
  private String discontinued;
  private CompanyDto company;

  /**
   * Instantiates a new computer dto.
   *
   * @param id the id of the computer
   * @param name the name of the computer
   * @param introduced the introduced date
   * @param discontinued the discontinued date
   * @param company the related company
   */
  public ComputerDto(long id, String name, String introduced, String discontinued,
      CompanyDto company) {
    this.id = id;
    this.name = name;
    this.introduced = introduced;
    this.discontinued = discontinued;
    this.company = company;
  }

  public ComputerDto() {

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
