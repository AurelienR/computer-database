package com.excilys.cdb.cli.impl;

import com.excilys.cdb.cli.CliException;
import com.excilys.cdb.cli.Command;
import com.excilys.cdb.cli.InputCommandParser;
import com.excilys.cdb.daos.DaoException;
import com.excilys.cdb.models.Computer;
import com.excilys.cdb.services.ServiceException;

import java.util.List;
import java.util.Scanner;

/**
 * CLI to display computer details
 * 
 * @author Aurelien.R
 *
 */
public class DisplayComputerDetailsCmd implements Command {

  private Scanner sc;

  public DisplayComputerDetailsCmd(Scanner sc) {
    this.sc = sc;
  }

  @Override
  public void execute() {
    try {
      System.out.println("Find computer details:");
      // Get computer name input
      System.out.println("Name (of existing computer):");
      sc.nextLine();
      List<Computer> computers = InputCommandParser.getValidComputerByName(sc);
      for (Computer c : computers) {
        System.out.println(c);
      }

    } catch (IllegalArgumentException e) {
      throw new CliException("Illegal argument", e);
    } catch (ServiceException e) {
      throw new CliException("Illegal argument", e);
    } catch (DaoException e) {
      throw new CliException("DAO exception", e);
    }
  }
}
