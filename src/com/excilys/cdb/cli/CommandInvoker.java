package com.excilys.cdb.cli;

/**
 * Executer any command passed
 * Command Pattern
 * @author Aurelien.R
 *
 */
public class CommandInvoker {
	Command command;

	/**
	 * Set command that can be executed
	 * @param command
	 */
	public void setCommand(Command command) {
		this.command = command;
	}

	/**
	 * Execute the last command set
	 */
	public void invoke(){
		command.execute();
	}	
}
