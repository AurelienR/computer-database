package com.excilys.cdb.cli.impl;

import com.excilys.cdb.cli.Command;

import org.springframework.stereotype.Component;

/**
 * CLI to exit the program
 * 
 * @author Aurelien.R
 *
 */
@Component
public class ExitCmd implements Command {

  public ExitCmd() {
  }

  @Override
  public void execute() {
    System.out.println("Exiting Computer Database");
    System.exit(0);
  }
}
