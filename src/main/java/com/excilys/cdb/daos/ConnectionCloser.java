package com.excilys.cdb.daos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Utility class to close properly connection and related objects
 * 
 * @author Aurelien.R
 *
 */
public class ConnectionCloser {

	/**
	 * Close resultSet related to a DB Connection
	 * 
	 * @param resultSet
	 *            resultSet to close
	 * @throws DAOException
	 */
	public static void silentClose(ResultSet resultSet) throws DAOException {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				throw new DAOException("Failure closing ResultSet", e);
			}

		}
	}

	/**
	 * Close statement related to a DB Connection
	 * 
	 * @param statement
	 *            statement to close
	 * @throws DAOException
	 */
	public static void silentClose(Statement statement) throws DAOException {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				throw new DAOException("Failure closing Statement", e);
			}
		}
	}

	/**
	 * Close connection related to a DB Connection
	 * 
	 * @param connection
	 *            connection to close
	 * @throws DAOException
	 */
	public static void silentClose(Connection connection) throws DAOException {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new DAOException("Failure closing connextion", e);
			}
		}
	}

	/**
	 * Close multiple DB connection element
	 * 
	 * @param statement
	 *            statement to close
	 * @param connexion
	 *            connection to close
	 * @throws DAOException
	 */
	public static void silentCloses(Statement statement, Connection connexion) throws DAOException {
		silentClose(statement);
		silentClose(connexion);
	}

	/**
	 * Close multiple DB connection element
	 * 
	 * @param resultSet
	 *            resultSet to close
	 * @param statement
	 *            statement to close
	 * @param connexion
	 *            connection to close
	 * @throws DAOException
	 */
	public static void silentCloses(ResultSet resultSet, Statement statement, Connection connexion)
			throws DAOException {
		silentClose(resultSet);
		silentClose(statement);
		silentClose(connexion);
	}
}
