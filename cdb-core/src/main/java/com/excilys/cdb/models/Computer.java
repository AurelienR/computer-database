package com.excilys.cdb.models;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Model of a Computer
 * 
 * @author Aurelien.R
 *
 */
@Entity
@Table(name = "computer")
public class Computer {

  // Attributes
  @Id
  private long id = 0;
  @Column(nullable = true)
  private String name;
  @Column(nullable = true)
  private LocalDateTime discontinued;
  @Column(nullable = true)
  private LocalDateTime introduced;
  @OneToOne
  @JoinColumn(name = "company_id")
  @NotFound(action = NotFoundAction.IGNORE)
  private Company company;

  /**
   * Instantiates a new computer.
   *
   * @param id the id
   * @param name the name of the computer
   * @param company the r elated company
   * @param discontinued the discontinued date
   * @param introduced the introduced date
   */
  // Constructors
  public Computer(long id, String name, Company company, LocalDateTime discontinued,
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
  public long getId() {
    return id;
  }

  public void setId(long id) {
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
    result = prime * result + (int) (id ^ (id >>> 32));
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
    if (id != other.id) {
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
