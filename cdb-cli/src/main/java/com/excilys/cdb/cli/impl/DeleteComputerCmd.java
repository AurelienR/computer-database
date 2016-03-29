package com.excilys.cdb.cli.impl;

import com.excilys.cdb.cli.CliException;
import com.excilys.cdb.cli.Command;
import com.excilys.cdb.network.CliRequestManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

/**
 * CLI to delete a computer
 *
 * @author Aurelien.R
 *
 */
public class DeleteComputerCmd implements Command {

  // Logger
  static final Logger LOGGER = LoggerFactory.getLogger(DeleteComputerCmd.class);

  private Scanner sc;

  public DeleteComputerCmd(Scanner sc) {
    this.sc = sc;
  }

  @Override
  public void execute() {

    try {
      System.out.println("Delete computer:");

      // Get computer name input
      System.out.println("Id (of existing computer):");
      sc.nextLine();
      long id = sc.nextLong();

      // Delete computer
      LOGGER.debug("Try to delete computer with id: {}", id);
      CliRequestManager.deleteComputer(id);

    } catch (IllegalArgumentException e) {
      throw new CliException("Illegal argument", e);
    }
  }

}
