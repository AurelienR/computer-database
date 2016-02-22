package com.excilys.cdb.daos;

/**
 * Custom exception for config and setup DB connnection
 * 
 * @author Aurelien.R
 *
 */
public class DaoConfigurationException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public DaoConfigurationException(String message) {
    super(message);
  }

  public DaoConfigurationException(String message, Throwable cause) {
    super(message, cause);
  }

  public DaoConfigurationException(Throwable cause) {
    super(cause);
  }
}
