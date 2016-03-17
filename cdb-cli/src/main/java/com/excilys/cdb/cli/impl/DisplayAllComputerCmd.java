package com.excilys.cdb.cli.impl;

import com.excilys.cdb.cli.Command;
import com.excilys.cdb.models.Computer;
import com.excilys.cdb.services.ComputerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
* CLI to display all computers
*
* @author Aurelien.R
*
*/
@Component
public class DisplayAllComputerCmd implements Command {

  // Service
  @Autowired
  private ComputerService computerService;

  public DisplayAllComputerCmd() {
  }

  @Override
  public void execute() {

    System.out.println("Display all computers...");
    for (Computer c : computerService.findAll()) {
      System.out.println(c);
    }

  }
}
