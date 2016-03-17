package com.excilys.cdb.cli.impl;

import com.excilys.cdb.cli.CliException;
import com.excilys.cdb.cli.Command;
import com.excilys.cdb.cli.InputCommandParser;
import com.excilys.cdb.models.Company;
import com.excilys.cdb.services.CompanyService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Scanner;

/**
 * CLI to delete a computer computer *
 *
 * @author Aurelien.R
 *
 */
@Component
@Scope("prototype")
public class DeleteCompanyCmd implements Command {

  // Logger
  private static final Logger LOGGER = LoggerFactory.getLogger(DeleteCompanyCmd.class);

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
      LOGGER.debug("Try to delete company: {}",company);
      companyService.deleteCompany(company.getId());

    } catch (IllegalArgumentException e) {
      throw new CliException("Illegal argument", e);
    }
  }

}
