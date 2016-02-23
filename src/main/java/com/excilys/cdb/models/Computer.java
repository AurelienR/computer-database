package com.excilys.cdb.models;

import com.excilys.cdb.validators.bean.ComputerDates;

import java.time.LocalDateTime;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Model of a Computer
 * 
 * @author Aurelien.R
 *
 */

@ComputerDates
public class Computer {

  // Attributes
  @Min(value = 0, message = "Computer id doit être supérieur à {value}")
  private int id = 0;
  private Company company;
  @NotNull(message = "Computer name should be not null")
  @Size(min = 0, message = "Computer name cannot be empty string")
  private String name;
  private LocalDateTime discontinued;
  private LocalDateTime introduced;

  /**
   * Instantiates a new computer.
   *
   * @param id
   *          the id
   * @param name
   *          the name of the computer
   * @param company
   *          the related company
   * @param discontinued
   *          the discontinued date
   * @param introduced
   *          the introduced date
   */
  // Constructors
  public Computer(int id, String name, Company company, LocalDateTime discontinued,
      LocalDateTime introduced) {
    this.id = id;
    this.name = name;
    this.company = company;
    this.discontinued = discontinued;
    this.introduced = introduced;
  }

  public Computer() {
  }

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
  public String toString() {
    return "Computer [id=" + id + ", company=" + company + ", name=" + name + ", discontinued="
        + discontinued + ", introduced=" + introduced + "]";
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
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Computer other = (Computer) obj;
    if (company == null) {
      if (other.company != null) {
        return false;
      }
    } else if (!company.equals(other.company)) {
      return false;
    }
    if (discontinued == null) {
      if (other.discontinued != null) {
        return false;
      }
    } else if (!discontinued.equals(other.discontinued)) {
      return false;
    }
    if (id != other.id) {
      return false;
    }
    if (introduced == null) {
      if (other.introduced != null) {
        return false;
      }
    } else if (!introduced.equals(other.introduced)) {
      return false;
    }
    if (name == null) {
      if (other.name != null) {
        return false;
      }
    } else if (!name.equals(other.name)) {
      return false;
    }
    return true;
  }

}
