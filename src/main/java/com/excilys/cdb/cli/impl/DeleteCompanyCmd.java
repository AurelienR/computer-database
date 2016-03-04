package com.excilys.cdb.cli.impl;

import com.excilys.cdb.cli.CliException;
import com.excilys.cdb.cli.Command;
import com.excilys.cdb.cli.InputCommandParser;
import com.excilys.cdb.daos.DaoException;
import com.excilys.cdb.models.Company;
import com.excilys.cdb.services.CompanyService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Scanner;

/**
 * CLI to delete a computer computer *
 * 
 * @author Aurelien.R
 *
 */
public class DeleteCompanyCmd implements Command {

  // Logger
  static final Logger logger = LoggerFactory.getLogger(DeleteCompanyCmd.class);

  // Parser
  @Autowired
  private InputCommandParser inputCmdParser;
  // Service
  @Autowired
  private CompanyService companyService;

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
      Company company = inputCmdParser.getRequiredValidCompanyByName(sc).get(0);

      // Delete company
      logger.debug("Try to delete company: " + company);
      companyService.deleteCompany(company.getId());

    } catch (IllegalArgumentException e) {
      throw new CliException("Illegal argument", e);
    } catch (DaoException e) {
      throw new CliException("DAO exception", e);
    }
  }

}
