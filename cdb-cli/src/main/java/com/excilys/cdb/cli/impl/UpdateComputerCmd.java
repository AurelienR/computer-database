package com.excilys.cdb.cli.impl;

import com.excilys.cdb.cli.Command;
import com.excilys.cdb.dtos.CompanyDto;
import com.excilys.cdb.dtos.ComputerDto;
import com.excilys.cdb.network.CliRequestManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

/**
 * CLI to update a computer
 *
 * @author Aurelien.R
 *
 */
public class UpdateComputerCmd implements Command {

  // Logger
  private static final Logger LOGGER = LoggerFactory.getLogger(UpdateComputerCmd.class);

  private final String dateFormat = "MM/dd/yyyy";
  private Scanner sc;

  public UpdateComputerCmd(Scanner sc) {
    this.sc = sc;
  }

  @Override
  public void execute() {

    System.out.println("Update a computer:");

    ComputerDto computerDto = new ComputerDto();

    // Get name input [required]
    System.out.println("Id of the computer to update:");
    sc.nextLine();
    long id = sc.nextLong();
    LOGGER.debug("Id computer to update: {}", id);

    // Get new name input
    System.out.println("New Name:");
    String newName = sc.next();
    if (!newName.isEmpty()) {
      LOGGER.debug("computer.name: {}", newName);
      computerDto.setName(newName);
    }

    // Get
    System.out.println("Introduced (" + dateFormat + "): ");
    sc.nextLine();
    String introDate = sc.next();
    if (introDate != null) {
      LOGGER.debug("computer.introduced:{} ", introDate);
      computerDto.setIntroduced(introDate);
    }

    System.out.println("Discontinued (" + dateFormat + "): ");
    String discDate = sc.next();
    if (introDate != null) {
      LOGGER.debug("computer.discontinued: {}", discDate);
      computerDto.setIntroduced(discDate);
    }

    System.out.println("CompanyId ( must match existring one): ");
    long companyId = sc.nextLong();
    if (companyId > 0) {
      computerDto.setCompany(new CompanyDto(companyId, ""));
    }

    LOGGER.debug("Try to update computer: {}", computerDto);
    CliRequestManager.updateComputer(computerDto);

  }

}
