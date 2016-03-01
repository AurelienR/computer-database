package com.excilys.cdb.daos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

/**
 * Single entry point to DAO objects Manage Connection and driver DB connection.
 *
 * @author Aurelien.R
 */
@Component
public class ConnectionFactory {

  @Autowired
  private DataSource dataSource;
  private static ConnectionFactory instance;

  public static ConnectionFactory getInstance() {
    if (instance == null) {
      instance = new ConnectionFactory();
    }
    return instance;
  }

  private DataSource getDataSource() {
    return dataSource;
  }

  public void setDataSource(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  private ConnectionFactory() {
  }

  protected final Connection getConnection() {
    try {
      return dataSource.getConnection();
    } catch (SQLException e) {
      throw new DaoConfigurationException("Unable to get connection: " + e.getMessage(), e);
    }
  }
}