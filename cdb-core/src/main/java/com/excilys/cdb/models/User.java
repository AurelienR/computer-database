package com.excilys.cdb.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_entity")
public class User {
  
  @Id
  long id;
  @Column
  String username;
  @Column
  String password;
  @ManyToOne
  @JoinColumn(name = "user_role_id")
  UserRole role;
  
  public long getId() {
    return id;
  }
  public void setId(long id) {
    this.id = id;
  }
  public String getUsername() {
    return username;
  }
  public void setUsername(String username) {
    this.username = username;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public UserRole getRole() {
    return role;
  }
  public void setRole(UserRole role) {
    this.role = role;
  }
  
}
