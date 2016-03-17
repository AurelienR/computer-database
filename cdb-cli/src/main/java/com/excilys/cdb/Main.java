package com.excilys.cdb;

import com.excilys.cdb.cli.CommandParser;

public class Main {

  /**
   * The main method.
   *
   * @param args
   *          the arguments passed to the program
   */
  public static void main(String[] args) {

    CommandParser cp = new CommandParser();
    cp.welcome();
    while (true) {
      cp.displayAvailableCmds();
      cp.parseCmd();
    }
  }

}
