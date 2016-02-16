package com.excilys.cdb.cli.impl;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.cli.CLIException;
import com.excilys.cdb.cli.Command;
import com.excilys.cdb.cli.InputCommandParser;
import com.excilys.cdb.daos.DAOException;
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

	// Logger
	final static Logger logger = LoggerFactory.getLogger(CreateComputerCmd.class);
	
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
			logger.debug("computer.name: "+ computerName);
			computer.setName(computerName);			

			// Get introduced date input
			System.out.println("Introduced date (" + dateFormat + "): ");
			sc.nextLine();
			LocalDateTime introDate = InputCommandParser.getDateInput(sc, dateFormat);
			logger.debug("computer.introduced: "+ introDate.toString());
			computer.setIntroduced(introDate);			

			// Get discontinued date input
			System.out.println("Discontinued date (" + dateFormat + "): ");
			LocalDateTime discDate = InputCommandParser.getDateInput(sc, dateFormat);
			logger.debug("computer.discontinued: "+ discDate.toString());
			computer.setDiscontinued(discDate);
			
			// Get company input
			System.out.println("CompanyName ( must match existring one): ");
			Company company = InputCommandParser.getRequiredValidCompanyByName(sc).get(0);
			logger.debug("computer.company: "+ company.toString());
			computer.setCompany(company);
			
			// Create computer
			logger.debug("Try to create computer: "+ computer);
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
