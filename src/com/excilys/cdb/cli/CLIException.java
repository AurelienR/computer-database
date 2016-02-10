package com.excilys.cdb.cli;

public class CLIException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CLIException(String message) {
		super(message);
	}

	public CLIException(String message, Throwable cause) {
		super(message, cause);
	}

	public CLIException(Throwable cause) {
		super(cause);
	}
}
