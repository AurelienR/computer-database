package com.excilys.cdb.mappers;

import com.excilys.cdb.daos.impl.ComputerDaoImpl;
import com.excilys.cdb.models.Company;
import com.excilys.cdb.models.Computer;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class ComputerRowMapper implements RowMapper<Computer> {

  @Override
  public Computer mapRow(ResultSet rs, int row) throws SQLException {
    return mapSingleRow(rs);
  }

  /**
   * Map single row based on the resultSet passed.
   *
   * @param rs the rs
   * @return the computer
   * @throws SQLException the SQL exception
   */
  public static Computer mapSingleRow(ResultSet rs) throws SQLException {
    
    // Set computer fields
    // Id
    long computerId = rs.getLong("computer.id");
    // Name
    String computerName = rs.getString("computer.name");

    // Introduced
    Timestamp introTimestamp = rs.getTimestamp(ComputerDaoImpl.INTRO_COLUMN);
    LocalDateTime introduced;
    if (introTimestamp == null) {
      introduced = null;
    } else {
      introduced = introTimestamp.toLocalDateTime();
    }

    // Discontinued
    Timestamp descTimestamp = rs.getTimestamp(ComputerDaoImpl.DISC_COLUMN);
    LocalDateTime discontinued;
    if (descTimestamp == null) {
      discontinued = null;
    } else {
      discontinued = descTimestamp.toLocalDateTime();
    }

    // Initialize related company
    Company company = new Company(rs.getLong("company.id"), rs.getString("company.name"));
    // Initialize computer
    Computer computer = new Computer(computerId, computerName, company, discontinued, introduced);
    
    return computer;
  }
}
