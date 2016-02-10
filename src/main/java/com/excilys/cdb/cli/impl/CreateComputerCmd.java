package com.excilys.cdb.cli.impl;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Scanner;

import com.excilys.cdb.cli.CLIException;
import com.excilys.cdb.cli.Command;
import com.excilys.cdb.cli.InputCommandParser;
import com.excilys.cdb.dao.DAOException;
import com.excilys.cdb.models.Company;
import com.excilys.cdb.models.Computer;
import com.excilys.cdb.services.ComputerService;
import com.excilys.cdb.services.ServiceException;

/**
 * CLI to add a computer
 * 
 * @author Aurelien.R
 *
 */
public class CreateComputerCmd implements Command {

	private final String dateFormat = "dd/MM/yyyy";
	private Scanner sc;

	public CreateComputerCmd(Scanner sc) {
		this.sc = sc;
	};

	@Override
	public void execute() throws CLIException {
		Computer computer = new Computer();

		try {
			System.out.println("Create a computer:");

			// Get name input
			System.out.println("Name:");
			String computerName = InputCommandParser.getRequiredNameInput(sc);
			computer.setName(computerName);

			// Get introduced date input
			System.out.println("Introduced date (" + dateFormat + "): ");
			sc.nextLine();
			LocalDateTime introDate = InputCommandParser.getDateInput(sc, dateFormat);
			computer.setIntroduced(introDate);

			// Get discontinued date input
			System.out.println("Discontinued date (" + dateFormat + "): ");
			LocalDateTime discDate = InputCommandParser.getDateInput(sc, dateFormat);
			computer.setDiscontinued(discDate);

			// Get company input
			System.out.println("CompanyName ( must match existring one): ");
			Company company = InputCommandParser.getRequiredValidCompanyByName(sc).get(0);
			computer.setCompany(company);

			// Create computer
			ComputerService.getInstance().createComputer(computer);

		} catch (ParseException e) {
			throw new CLIException("Parsing exception", e);
		} catch (IllegalArgumentException e) {
			throw new CLIException("Illegal argument", e);
		} catch (ServiceException e) {
			throw new CLIException("Illegal argument", e);
		} catch (DAOException e) {
			throw new CLIException("DAO exception", e);
		}

	}

}
