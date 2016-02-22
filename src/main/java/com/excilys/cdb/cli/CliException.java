package com.excilys.cdb.cli;

/**
 * Exception for CLI execution
 * 
 * @author Aurelien.R
 *
 */
public class CliException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public CliException(String message) {
    super(message);
  }

  public CliException(String message, Throwable cause) {
    super(message, cause);
  }

  public CliException(Throwable cause) {
    super(cause);
  }
}
