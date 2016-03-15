package com.excilys.cdb.daos.repositories;

import com.excilys.cdb.models.Computer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComputerRepository extends JpaRepository<Computer, Long> {

  List<Computer> findByName(String name);

  @Query(value = "SELECT c FROM Computer c LEFT JOIN c.company cpy "
      + "WHERE (c.name LIKE %:search% OR cpy.name LIKE %:search% )")
  Page<Computer> findByNameOrCompanyName(@Param("search") String search, Pageable pageable);

  
  @Query(value = "SELECT COUNT(c) FROM Computer c LEFT JOIN c.company cpy "
      + "WHERE (c.name LIKE %:search% OR cpy.name LIKE %:search% )")
  long countByNameOrCompanyName(@Param("search")String searchName);

  void deleteByCompany_Id(long id);
}
