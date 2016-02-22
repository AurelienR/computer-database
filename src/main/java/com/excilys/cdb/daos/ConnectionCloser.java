package com.excilys.cdb.daos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// TODO: Auto-generated Javadoc
/**
 * Utility class to close properly connection and related objects.
 *
 * @author Aurelien.R
 */
public class ConnectionCloser {

  /**
   * Close resultSet related to a DB Connection.
   *
   * @param resultSet          resultSet to close
   * @throws DaoException           issue with db
   */
  public static void silentClose(ResultSet resultSet) throws DaoException {
    if (resultSet != null) {
      try {
        resultSet.close();
      } catch (SQLException e) {
        throw new DaoException("Failure closing ResultSet", e);
      }

    }
  }

  /**
   * Close statement related to a DB Connection.
   *
   * @param statement          statement to close
   * @throws DaoException           issue with db
   */
  public static void silentClose(Statement statement) throws DaoException {
    if (statement != null) {
      try {
        statement.close();
      } catch (SQLException e) {
        throw new DaoException("Failure closing Statement", e);
      }
    }
  }

  /**
   * Close connection related to a DB Connection.
   *
   * @param connection          connection to close
   * @throws DaoException           issue with db
   */
  public static void silentClose(Connection connection) throws DaoException {
    if (connection != null) {
      try {
        connection.close();
      } catch (SQLException e) {
        throw new DaoException("Failure closing connextion", e);
      }
    }
  }

  /**
   * Close multiple DB connection element.
   *
   * @param statement          statement to close
   * @param connexion          connection to close
   * @throws DaoException           issue with db
   */
  public static void silentCloses(Statement statement, Connection connexion) throws DaoException {
    silentClose(statement);
    silentClose(connexion);
  }

  /**
   * Close multiple DB connection element.
   *
   * @param resultSet          resultSet to close
   * @param statement          statement to close
   * @param connexion          connection to close
   * @throws DaoException           issue with db
   */
  public static void silentCloses(ResultSet resultSet, Statement statement, Connection connexion)
      throws DaoException {
    silentClose(resultSet);
    silentClose(statement);
    silentClose(connexion);
  }
}
