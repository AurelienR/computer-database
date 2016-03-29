package com.excilys.cdb.cli.impl;

import com.excilys.cdb.cli.Command;
import com.excilys.cdb.network.CliRequestManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

/**
 * CLI to delete a computer computer *
 *
 * @author Aurelien.R
 *
 */
public class DeleteCompanyCmd implements Command {

  // Logger
  private static final Logger LOGGER = LoggerFactory.getLogger(DeleteCompanyCmd.class);

  private Scanner sc;

  public DeleteCompanyCmd(Scanner sc) {
    this.sc = sc;
  }

  @Override
  public void execute() {

      System.out.println("Delete company:");

      // Get computer name input
      System.out.println("Id (of existing company):");
      sc.nextLine();
      long id =  sc.nextLong();

      // Delete company
      LOGGER.debug("Try to delete company: {}",id);
      CliRequestManager.deleteCompany(id);
  }

}
