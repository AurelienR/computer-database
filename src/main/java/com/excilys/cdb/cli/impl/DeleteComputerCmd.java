package com.excilys.cdb.cli.impl;

import com.excilys.cdb.cli.CliException;
import com.excilys.cdb.cli.Command;
import com.excilys.cdb.cli.InputCommandParser;
import com.excilys.cdb.daos.DaoException;
import com.excilys.cdb.models.Computer;
import com.excilys.cdb.services.ComputerService;
import com.excilys.cdb.services.ServiceException;

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
  static final Logger logger = LoggerFactory.getLogger(DeleteComputerCmd.class);

  private Scanner sc;

  public DeleteComputerCmd(Scanner sc) {
    this.sc = sc;
  }

  @Override
  public void execute() {

    try {
      System.out.println("Delete computer:");

      // Get computer name input
      System.out.println("Name (of existing computer):");
      sc.nextLine();
      Computer computer = InputCommandParser.getValidComputerByName(sc).get(0);

      // Delete computer
      logger.debug("Try to delete computer: " + computer);
      ComputerService.getInstance().deleteComputer(computer.getId());

    } catch (IllegalArgumentException e) {
      throw new CliException("Illegal argument", e);
    } catch (ServiceException e) {
      throw new CliException("Illegal argument", e);
    } catch (DaoException e) {
      throw new CliException("DAO exception", e);
    }
  }

}
