package com.excilys.cdb.cli.impl;

import java.util.List;
import java.util.Scanner;

import com.excilys.cdb.cli.CLIException;
import com.excilys.cdb.cli.Command;
import com.excilys.cdb.cli.InputCommandParser;
import com.excilys.cdb.dao.DAOException;
import com.excilys.cdb.models.Computer;
import com.excilys.cdb.services.ServiceException;

/**
 * CLI to display computer details
 * 
 * @author Aurelien.R
 *
 */
public class DisplayComputerDetailsCmd implements Command {

	private Scanner sc;

	public DisplayComputerDetailsCmd(Scanner sc) {
		this.sc = sc;
	};

	@Override
	public void execute() {
		try {
			System.out.println("Find computer details:");
			// Get computer name input
			System.out.println("Name (of existing computer):");
			sc.nextLine();
			List<Computer> computers = InputCommandParser.getValidComputerByName(sc);
			for (Computer c : computers) {
				System.out.println(c);
			}

		} catch (IllegalArgumentException e) {
			throw new CLIException("Illegal argument", e);
		} catch (ServiceException e) {
			throw new CLIException("Illegal argument", e);
		} catch (DAOException e) {
			throw new CLIException("DAO exception", e);
		}
	}
}
