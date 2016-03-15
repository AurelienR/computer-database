package com.excilys.cdb.daos.repositories;

import com.excilys.cdb.models.Company;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Long> {

  List<Company> findByName(String lastname);
  
}