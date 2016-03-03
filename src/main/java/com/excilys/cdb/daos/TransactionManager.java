package com.excilys.cdb.daos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;

@Component
public class TransactionManager {

  // Logger
  private static final Logger logger = LoggerFactory.getLogger(TransactionManager.class);

  // Attributes
  private static final ThreadLocal<Connection> context = new ThreadLocal<Connection>();
  private static TransactionManager instance;

  // Constructors
  private TransactionManager() {
  }

  // Methods
  /**
   * Gets the single instance of TransactionManager. Singleton.
   *
   * @return single instance of TransactionManager
   */
  public static TransactionManager getInstance() {
    if (instance == null) {
      instance = new TransactionManager();
    }
    return instance;
  }

  public void startTransaction() throws DaoConfigurationException, SQLException {
    logger.debug("Starting transaction, retrieve con -> set autoCommit false ");
    getConnection().setAutoCommit(false);
  }

  /**
   * Get connection and set it in local thread.
   *
   * @return the connection from the local thread
   * @throws TransactionManagerException
   *           if fail to get connection from connection factory
   */
  public Connection getConnection() throws TransactionManagerException {

    logger.debug("Getting connection from connection factory");

    if (context.get() == null) {
      Connection con = ConnectionFactory.getInstance().getConnection();
      context.set(con);
      logger.debug("Set Connection in thread local");
    }
    return context.get();
  }

  /**
   * Commit on current connection.
   *
   * @throws TransactionManagerException
   *           Failed to commit
   */
  public void commit() throws TransactionManagerException {

    logger.debug("Committing on current connection");
    Connection con = context.get();

    try {
      con.commit();
    } catch (SQLException e) {
      logger.error("Failed to commit");
      throw new TransactionManagerException("Failed to commit");
    }
  }

  /**
   * Rollback the transaction with the current thread connection.
   *
   * @throws TransactionManagerException
   *           if cannot rollback with the current thread connection
   */
  public void rollback() throws TransactionManagerException {

    logger.debug("Rollback current transaction");
    Connection con = context.get();

    try {
      con.rollback();
    } catch (SQLException e) {
      logger.error("Failed to rollback current transaction");
      throw new TransactionManagerException("Cannot rollback with the current connection", e);
    }
  }

  /**
   * Try to close connection if auto-commit is true and remove it from local thread.
   *
   * @throws TransactionManagerException
   *           if failed to get autocommit or close connection
   */
  public void close() throws TransactionManagerException {

    Connection con = context.get();
    try {
      if (con.getAutoCommit()) {
        logger.debug("Close connection and remove it from local thread");
        ConnectionCloser.silentClose(con);
        context.remove();
      }
    } catch (SQLException e) {
      logger.error("Failed to check autocommit or close connection");
      throw new TransactionManagerException("Cannot try to close connection", e);
    }
  }

  /**
   * End transaction by closing connection and removing it from local thread.
   */
  public void endTransaction() {

    logger.debug("End Transaction, close connection and remove it from local thread");
    Connection con = context.get();
    ConnectionCloser.silentClose(con);
    context.remove();
  }
}
