package com.excilys.cdb.daos;

/**
 * Custom Exception for transaction.
 */
public class TransactionManagerException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public TransactionManagerException(String message) {
    super(message);
  }

  public TransactionManagerException(String message, Throwable cause) {
    super(message, cause);
  }

  public TransactionManagerException(Throwable cause) {
    super(cause);
  }
}
