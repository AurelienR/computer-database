package com.excilys.cdb.cli.impl;

import com.excilys.cdb.cli.CliException;
import com.excilys.cdb.cli.Command;
import com.excilys.cdb.daos.DaoException;
import com.excilys.cdb.models.Computer;
import com.excilys.cdb.services.ComputerService;
import com.excilys.cdb.services.ServiceException;

/**
 * CLI to display all computers
 * 
 * @author Aurelien.R
 *
 */
public class DisplayAllComputerCmd implements Command {

  public DisplayAllComputerCmd() {
  }

  @Override
  public void execute() {

    try {
      System.out.println("Display all computers...");
      for (Computer c : ComputerService.getInstance().findAll()) {
        System.out.println(c);
      }
    } catch (ServiceException e) {
      throw new CliException("Illegal argument", e);
    } catch (DaoException e) {
      throw new CliException("DAO exception", e);
    }
  }
}
