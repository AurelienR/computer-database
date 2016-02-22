package com.excilys.cdb.daos;

/**
 * Custom exception for DAO database access
 * 
 * @author Aurelien.R
 *
 */
public class DaoException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public DaoException(String message) {
    super(message);
  }

  public DaoException(String message, Throwable cause) {
    super(message, cause);
  }

  public DaoException(Throwable cause) {
    super(cause);
  }
}