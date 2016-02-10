package com.excilys.cdb.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.sql.Connection;

/**
 * Single entry point to DAO objects Manage Connection and driver DB connection
 * 
 * @author Aurelien.R
 */

public class ConnectionFactory {

	private static final String PROPERTY_FILE = "dao.properties";
	private static final String URL_PROPERTY = "url";
	private static final String DRIVER_PROPERTY = "driver";
	private static final String USERNAME_PROPERTY = "nomutilisateur";
	private static final String PASSWORD_PROPERTY = "motdepasse";

	private String url;
	private String username;
	private String password;

	// Singleton
	private static ConnectionFactory instance;

	private ConnectionFactory(String url, String username, String password) {
		this.url = url;
		this.username = username;
		this.password = password;
	}

	/**
	 * Get instance of ConnectionFactory object correctly initialize
	 * 
	 * @return ConnectionFactory object that give access to connections
	 * @throws DAOConfigurationException
	 */
	public static ConnectionFactory getInstance() throws DAOConfigurationException {
		if (instance == null) {
			instance = initializeInstance();
		}
		return instance;
	}

	/**
	 * Initialize driver of the ConnectionFactory
	 * 
	 * @return ConnectionFactory instance with drive initialized
	 * @throws DAOConfigurationException
	 */
	private static ConnectionFactory initializeInstance() throws DAOConfigurationException {

		// Local variables initialization
		Properties properties = new Properties();
		String url = null;
		String driver = null;
		String nomUtilisateur = null;
		String motDePasse = null;

		// Read DAO file property
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream fileProperties = classLoader.getResourceAsStream(PROPERTY_FILE);

		// Check DAO file has been read correctly
		if (fileProperties == null) {
			throw new DAOConfigurationException("Property file " + PROPERTY_FILE + " not found.");
		}

		// Extract file property fields
		try {
			properties.load(fileProperties);
			url = properties.getProperty(URL_PROPERTY);
			driver = properties.getProperty(DRIVER_PROPERTY);
			nomUtilisateur = properties.getProperty(USERNAME_PROPERTY);
			motDePasse = properties.getProperty(PASSWORD_PROPERTY);

		} catch (IOException e) {
			throw new DAOConfigurationException("Cannot load propertied " + PROPERTY_FILE, e);
		}

		// Setup driver connection to DB
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			throw new DAOConfigurationException("Driver is missiing from classpath.", e);
		}

		// Instantiate and return single DAOFactory instance
		ConnectionFactory connectionFactory = new ConnectionFactory(url, nomUtilisateur, motDePasse);

		return connectionFactory;
	}

	/**
	 * Open a connection to DB based on property
	 * 
	 * @return Connection to DB
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}
}