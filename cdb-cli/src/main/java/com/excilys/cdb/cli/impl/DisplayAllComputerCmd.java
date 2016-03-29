package com.excilys.cdb.cli.impl;

import com.excilys.cdb.cli.Command;
import com.excilys.cdb.dtos.ComputerDto;
import com.excilys.cdb.dtos.ComputerPageDto;
import com.excilys.cdb.network.CliRequestManager;

import java.util.Scanner;

/**
 * CLI to display all computers
 *
 * @author Aurelien.R
 *
 */
public class DisplayAllComputerCmd implements Command {

  private Scanner sc;

  public DisplayAllComputerCmd(Scanner sc) {
    this.sc = sc;
  }

  public void execute() {

    System.out.println("Display all computers...");
    int page = 0;
    ComputerPageDto computerPageDto = CliRequestManager.getComputerPageDto(page);

    String cmd = "q";

    do {
      for (ComputerDto c : computerPageDto.getComputers()) {
        System.out.println(c);
      }

      System.out.println("********************************* Page: " + computerPageDto.getCurrent()
          + "/" + computerPageDto.getPageCount() + " *********************************");
      System.out.println("Next: n");
      System.out.println("Previous: p");
      System.out.println("Quit: q");

      cmd = sc.next();

      if (cmd.equals("n")) {
        if (computerPageDto.getCurrent() >= computerPageDto.getPageCount()) {
          page = computerPageDto.getCurrent() - 1;
        } else {
          page = computerPageDto.getCurrent();
        }
      } else if (cmd.equals("p")) {
        if (computerPageDto.getCurrent() <= 1) {
          page = 0;
        } else {
          page = computerPageDto.getCurrent() - 2;
        }
      }

      computerPageDto = CliRequestManager.getComputerPageDto(page);

    } while (!cmd.equals("q"));

  }
}
