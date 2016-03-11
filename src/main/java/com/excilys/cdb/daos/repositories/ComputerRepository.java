package com.excilys.cdb.daos.repositories;

import com.excilys.cdb.models.Computer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComputerRepository extends JpaRepository<Computer, Long> {

}
