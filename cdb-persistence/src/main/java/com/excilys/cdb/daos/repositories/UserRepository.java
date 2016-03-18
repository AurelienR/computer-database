package com.excilys.cdb.daos.repositories;

import com.excilys.cdb.models.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Long> {
  User findByUsername(String userName);
}
