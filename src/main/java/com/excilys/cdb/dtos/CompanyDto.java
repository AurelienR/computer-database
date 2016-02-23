package com.excilys.cdb.dtos;

public class CompanyDto {

  public int id;
  public String name;

  public CompanyDto(int id, String name) {
    this.id = id;
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
  public String toString() {
    return "CompanyDto [id=" + id + ", name=" + name + "]";
  }
  
  
}
