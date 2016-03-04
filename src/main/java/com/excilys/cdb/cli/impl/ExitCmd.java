package com.excilys.cdb.cli.impl;

import com.excilys.cdb.cli.Command;

/**
 * CLI to exit the program
 * 
 * @author Aurelien.R
 *
 */
public class ExitCmd implements Command {

  public ExitCmd() {
  }

  @Override
  public void execute() {
    System.out.println("Exiting Computer Database");
    System.exit(0);
  }
}
