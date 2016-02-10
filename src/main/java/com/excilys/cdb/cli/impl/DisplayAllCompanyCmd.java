package com.excilys.cdb.cli.impl;

import com.excilys.cdb.cli.CLIException;
import com.excilys.cdb.cli.Command;
import com.excilys.cdb.dao.DAOException;
import com.excilys.cdb.models.Company;
import com.excilys.cdb.services.CompanyService;
import com.excilys.cdb.services.ServiceException;

/**
 * CLI to display all companies
 * 
 * @author Aurelien.R
 *
 */
public class DisplayAllCompanyCmd implements Command {

	public DisplayAllCompanyCmd() {
	};

	@Override
	public void execute() {
		try {
			System.out.println("Display all companies...");
			for (Company c : CompanyService.getInstance().findAll()) {
				System.out.println(c);
			}
		} catch (ServiceException e) {
			throw new CLIException("Illegal argument", e);
		} catch (DAOException e) {
			throw new CLIException("DAO exception", e);
		}
	}

}
