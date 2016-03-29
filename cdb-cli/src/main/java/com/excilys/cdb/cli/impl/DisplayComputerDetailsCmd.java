package com.excilys.cdb.cli.impl;

import com.excilys.cdb.cli.Command;
import com.excilys.cdb.dtos.ComputerDto;
import com.excilys.cdb.network.CliRequestManager;

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
    System.out.println("Find computer details:");
    // Get computer name input
    System.out.println("Id (of existing computer):");
    sc.nextLine();
    long id = sc.nextLong();
    ComputerDto computerDto = CliRequestManager.getComputerById(id);
    System.out.println(computerDto);

  }
}
