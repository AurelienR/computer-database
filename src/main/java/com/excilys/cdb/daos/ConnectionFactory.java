package com.excilys.cdb.daos;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

// TODO: Auto-generated Javadoc
/**
 * Single entry point to DAO objects Manage Connection and driver DB connection.
 *
 * @author Aurelien.R
 */

public class ConnectionFactory {

  /** The Constant PROPERTY_FILE. */
  private static final String PROPERTY_FILE = "./properties/dao.properties";
  
  /** The Constant URL_PROPERTY. */
  private static final String URL_PROPERTY = "url";
  
  /** The Constant DRIVER_PROPERTY. */
  private static final String DRIVER_PROPERTY = "driver";
  
  /** The Constant USERNAME_PROPERTY. */
  private static final String USERNAME_PROPERTY = "nomutilisateur";
  
  /** The Constant PASSWORD_PROPERTY. */
  private static final String PASSWORD_PROPERTY = "motdepasse";
  
  /** The Constant MIN_CON_PROPERTY. */
  private static final String MIN_CON_PROPERTY = "MinConnectionsPerPartition";
  
  /** The Constant MAX_CON_PROPERTY. */
  private static final String MAX_CON_PROPERTY = "MaxConnectionsPerPartition";
  
  /** The Constant PARTITION_COUNT_PROPERTY. */
  private static final String PARTITION_COUNT_PROPERTY = "PartitionCount";

  /** The connection pool. */
  static BoneCP connectionPool = null;

  /** The instance. */
  // Singleton
  private static ConnectionFactory instance;

  /**
   * Instantiates a new connection factory.
   *
   * @param connectionPool the connection pool
   */
  // Constructor
  ConnectionFactory(BoneCP connectionPool) {
    ConnectionFactory.connectionPool = connectionPool;
  }

  /**
   * Get instance of ConnectionFactory object correctly initialize.
   *
   * @return ConnectionFactory object that give access to connections
   * @throws DaoConfigurationException           issue to get Dao configuration
   */
  public static ConnectionFactory getInstance() throws DaoConfigurationException {
    if (instance == null) {
      instance = initializeInstance();
    }
    return instance;
  }

  /**
   * Initialize connection pool of the ConnectionFactory.
   *
   * @return ConnectionFactory instance with connection pool initialized
   * @throws DaoConfigurationException           issue to get Dao configuration
   */
  private static ConnectionFactory initializeInstance() throws DaoConfigurationException {

    // Local variables initialization
    Properties properties = new Properties();
    String url = null;
    String driver = null;
    String nomUtilisateur = null;
    String motDePasse = null;
    int minConnections = 0;
    int maxConnections = 0;
    int partitionCount = 0;
    BoneCP connPool = null;

    // Read DAO file property
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    InputStream fileProperties = classLoader.getResourceAsStream(PROPERTY_FILE);

    // Check DAO file has been read correctly
    if (fileProperties == null) {
      throw new DaoConfigurationException("Property file " + PROPERTY_FILE + " not found.");
    }

    // Extract file property fields
    try {
      properties.load(fileProperties);
      url = properties.getProperty(URL_PROPERTY);
      driver = properties.getProperty(DRIVER_PROPERTY);
      nomUtilisateur = properties.getProperty(USERNAME_PROPERTY);
      motDePasse = properties.getProperty(PASSWORD_PROPERTY);
      minConnections = Integer.parseInt(properties.getProperty(MIN_CON_PROPERTY));
      maxConnections = Integer.parseInt(properties.getProperty(MAX_CON_PROPERTY));
      partitionCount = Integer.parseInt(properties.getProperty(PARTITION_COUNT_PROPERTY));

    } catch (IOException e) {
      throw new DaoConfigurationException("Cannot load propertied " + PROPERTY_FILE, e);
    }

    // Setup driver connection to DB
    try {
      Class.forName(driver);
    } catch (ClassNotFoundException e) {
      throw new DaoConfigurationException("Driver is missing from classpath.", e);
    }

    try {
      // Set up connection pool config
      BoneCPConfig config = new BoneCPConfig();

      config.setJdbcUrl(url);
      config.setUsername(nomUtilisateur);
      config.setPassword(motDePasse);

      config.setMinConnectionsPerPartition(minConnections);
      config.setMaxConnectionsPerPartition(maxConnections);
      config.setPartitionCount(partitionCount);

      connPool = new BoneCP(config);
    } catch (SQLException e) {
      e.printStackTrace();
      throw new DaoConfigurationException("Error in configuring connection pool", e);
    }

    // Instantiate and return single DAOFactory instance
    ConnectionFactory connectionFactory = new ConnectionFactory(connPool);

    return connectionFactory;
  }

  /**
   * Open a connection to DB based on property.
   *
   * @return Connection to DB
   * @throws SQLException issue with db
   */
  public Connection getConnection() throws SQLException {
    return connectionPool.getConnection();
  }
}