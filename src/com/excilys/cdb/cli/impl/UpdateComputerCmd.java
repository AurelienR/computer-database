package com.excilys.cdb.cli.impl;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import com.excilys.cdb.cli.CLIException;
import com.excilys.cdb.cli.Command;
import com.excilys.cdb.cli.InputCommandParser;
import com.excilys.cdb.dao.DAOException;
import com.excilys.cdb.models.Company;
import com.excilys.cdb.models.Computer;
import com.excilys.cdb.services.ComputerService;

/**
 * CLI to update a computer
 * 
 * @author Aurelien.R
 *
 */
public class UpdateComputerCmd implements Command {

	private final String dateFormat = "dd/MM/yyyy";
	private Scanner sc;

	public UpdateComputerCmd(Scanner sc) {
		this.sc = sc;
	}

	@Override
	public void execute() {

		try {
			System.out.println("Update a computer:");

			// Get name input [required]
			System.out.println("Name of the computer to update:");
			sc.nextLine();
			Computer newComputer = InputCommandParser.getRequiredValidComputerByName(sc).get(0);

			// Get new name input
			System.out.println("New Name:");
			String newName = InputCommandParser.getNameInput(sc);
			if (!newName.isEmpty()) {
				newComputer.setName(newName);
			}

			// Get
			System.out.println("Introduced (" + dateFormat + "): ");
			sc.nextLine();
			LocalDateTime introDate = InputCommandParser.getDateInput(sc, dateFormat);
			if (introDate != null) {
				newComputer.setIntroduced(introDate);
			}

			System.out.println("Discontinued (" + dateFormat + "): ");
			LocalDateTime discDate = InputCommandParser.getDateInput(sc, dateFormat);
			if (introDate != null) {
				newComputer.setIntroduced(discDate);
			}

			System.out.println("CompanyName ( must match existring one): ");
			List<Company> companies;
			if ((companies = InputCommandParser.getValidCompanyByName(sc)) != null) {
				Company company = companies.get(0);
				newComputer.setCompany(company);
			}

			ComputerService.getInstance().updateComputer(newComputer);

		} catch (ParseException e) {
			throw new CLIException("Parsing exception", e);
		} catch (IllegalArgumentException e) {
			throw new CLIException("Illegal argument", e);
		} catch (DAOException e) {
			throw new CLIException("DAO exception", e);
		}

	}

}
