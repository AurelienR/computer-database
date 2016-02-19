package com.excilys.cdb.cli.impl;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.cli.CLIException;
import com.excilys.cdb.cli.Command;
import com.excilys.cdb.cli.InputCommandParser;
import com.excilys.cdb.daos.DAOException;
import com.excilys.cdb.models.Company;
import com.excilys.cdb.services.CompanyService;
import com.excilys.cdb.services.ServiceException;

/**
 * CLI to delete a computer
computer * 
 * @author Aurelien.R
 *
 */
public class DeleteCompanyCmd implements Command {

	// Logger
	final static Logger logger = LoggerFactory.getLogger(DeleteCompanyCmd.class);
	
	private Scanner sc;

	public DeleteCompanyCmd(Scanner sc) {
		this.sc = sc;
	}

	@Override
	public void execute() {

		try {
			System.out.println("Delete company:");

			// Get computer name input
			System.out.println("Name (of existing company):");
			sc.nextLine();
			Company company = InputCommandParser.getRequiredValidCompanyByName(sc).get(0);
			
			// Delete company
			logger.debug("Try to delete company: "+ company);
			CompanyService.getInstance().deleteCompany(company.getId());

		} catch (IllegalArgumentException e) {
			throw new CLIException("Illegal argument", e);
		} catch (ServiceException e) {
			throw new CLIException("Illegal argument", e);
		} catch (DAOException e) {
			throw new CLIException("DAO exception", e);
		}
	}

}
