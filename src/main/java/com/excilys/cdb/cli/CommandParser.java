package com.excilys.cdb.cli;

import java.util.Scanner;

import com.excilys.cdb.cli.impl.CreateComputerCmd;
import com.excilys.cdb.cli.impl.DeleteComputerCmd;
import com.excilys.cdb.cli.impl.DisplayAllCompanyCmd;
import com.excilys.cdb.cli.impl.DisplayAllComputerCmd;
import com.excilys.cdb.cli.impl.DisplayComputerDetailsCmd;
import com.excilys.cdb.cli.impl.ExitCmd;
import com.excilys.cdb.cli.impl.UpdateComputerCmd;

/**
 * Commandline parser that manage client entries
 * 
 * @author Aurelien.R
 *
 */
public class CommandParser {

	// Constants
	private static final String LIST_COMPANIES_CMD = "listcompanies";
	private static final String LIST_COMPUTERS_CMD = "listcomputers";
	private static final String GET_COMPUTER_DETAILS_CMD = "computerdetails";
	private static final String CREATE_COMPUTER_CMD = "createcomputer";
	private static final String UPDATE_COMPUTER_CMD = "updatecomputer";
	private static final String DELETE_COMPUTER_CMD = "deletecomputer";
	private static final String EXIT_CMD = "exit";

	// Attributes
	private CommandInvoker cmdInvoker;
	private Scanner sc;

	// Constructors
	public CommandParser() {
		this.cmdInvoker = new CommandInvoker();
		this.sc = new Scanner(System.in);
	}

	// Methods
	/**
	 * Parse client input, and give instruction to follow
	 */
	public void parseCmd() {
		System.out.println("\nEnter a command:\n");
		String cmdStr = sc.next();
		switch (cmdStr) {
		case LIST_COMPANIES_CMD:
			cmdInvoker.setCommand(new DisplayAllCompanyCmd());
			break;
		case LIST_COMPUTERS_CMD:
			cmdInvoker.setCommand(new DisplayAllComputerCmd());
			break;
		case GET_COMPUTER_DETAILS_CMD:
			cmdInvoker.setCommand(new DisplayComputerDetailsCmd(sc));
			break;
		case CREATE_COMPUTER_CMD:
			cmdInvoker.setCommand(new CreateComputerCmd(sc));
			break;
		case UPDATE_COMPUTER_CMD:
			cmdInvoker.setCommand(new UpdateComputerCmd(sc));
			break;
		case DELETE_COMPUTER_CMD:
			cmdInvoker.setCommand(new DeleteComputerCmd(sc));
			break;
		case EXIT_CMD:
			cmdInvoker.setCommand(new ExitCmd());
			break;
		default:
			System.out.println("Command not found : " + cmdStr);
			return;

		}
		cmdInvoker.invoke();
	}

	/**
	 * Display in console all command line available for clients
	 */
	public void displayAvailableCmds() {
		StringBuilder sb = new StringBuilder("\n-----------------------------------------------------\n");
		sb.append("LIST OF COMMANDS:\n").append(LIST_COMPANIES_CMD).append('\n').append(LIST_COMPUTERS_CMD).append('\n')
				.append(GET_COMPUTER_DETAILS_CMD).append('\n').append(CREATE_COMPUTER_CMD).append('\n')
				.append(UPDATE_COMPUTER_CMD).append('\n').append(DELETE_COMPUTER_CMD).append('\n').append(EXIT_CMD)
				.append('\n').append("\n-----------------------------------------------------\n");
		System.out.println(sb);
	}

	/**
	 * Display welcome message in console to the client
	 */
	public void welcome() {
		StringBuilder sb = new StringBuilder("\n************************************************************\n");
		sb.append("COMPUTER DATABASE").append('\n')
				.append("************************************************************").append('\n');
		System.out.println(sb);
	}
}
