package com.excilys.cdb.daos;

/**
 * Custom exception for DAO database access
 * 
 * @author Aurelien.R
 *
 */
public class DAOException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DAOException(String message) {
		super(message);
	}

	public DAOException(String message, Throwable cause) {
		super(message, cause);
	}

	public DAOException(Throwable cause) {
		super(cause);
	}
}