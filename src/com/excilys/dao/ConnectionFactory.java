package com.excilys.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.excilys.dao.impl.CompanyDAOImpl;
import com.excilys.dao.impl.ComputerDAOImpl;

import java.sql.Connection;

/**
 * Single entry point to DAO objects Manage Connection and driver DB connection
 * 
 * @author Aurelien.R
 */

public class ConnectionFactory {

	private static final String PROPERTY_FILE = "dao/dao.properties";
	private static final String URL_PROPERTY = "url";
	private static final String DRIVER_PROPERTY = "driver";
	private static final String USERNAME_PROPERTY = "nomutilisateur";
	private static final String PASSWORD_PROPERTY = "motdepasse";

	private String url;
	private String username;
	private String password;

	ConnectionFactory(String url, String username, String password) {
		this.url = url;
		this.username = username;
		this.password = password;
	}

	/**
	 * Initialize drivers and db connections
	 * @return DAOFactory object that give access to connections
	 * @throws DAOConfigurationException
	 */
	public static ConnectionFactory getInstance() throws DAOConfigurationException {

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

		// Instanciate and return single DAOFactory instance
		ConnectionFactory instance = new ConnectionFactory(url, nomUtilisateur, motDePasse);

		return instance;

	}

	
	/**
	 * Open a connection to DB based on property 
	 * @return Connection to DB
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}

	/**
	 * @return new instance of ComputerDAO
	 */
	public ComputerDAO getComputerDAO() {
		return new ComputerDAOImpl(this);
	}

	/**
	 * @return new instance of CompanyDAO
	 */
	public CompanyDAO getCompanyDAO() {
		return new CompanyDAOImpl(this);
	}

}