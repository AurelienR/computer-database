package com.excilys.cdb.cli.impl;

import com.excilys.cdb.cli.CliException;
import com.excilys.cdb.cli.Command;
import com.excilys.cdb.dtos.CompanyDto;
import com.excilys.cdb.dtos.ComputerDto;
import com.excilys.cdb.network.CliRequestManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Scanner;

/**
 * CLI to add a computer
 *
 * @author Aurelien.R
 *
 */
@Component
@Scope("prototype")
public class CreateComputerCmd implements Command {

  // Logger
  private static final Logger LOGGER = LoggerFactory.getLogger(CreateComputerCmd.class);

  private final String dateFormat = "MM/dd/yyyy";
  private Scanner sc;

  public CreateComputerCmd(Scanner sc) {
    this.sc = sc;
  }

  @Override
  public void execute() throws CliException {
    ComputerDto computerDto = new ComputerDto();

    System.out.println("Create a computer:");

    // Get name input
    System.out.println("Name:");
    String computerName = sc.next();
    LOGGER.debug("computer.name: " + computerName);
    computerDto.setName(computerName);

    // Get introduced date input
    System.out.println("Introduced date (" + dateFormat + "): ");
    sc.nextLine();
    String introStr = sc.next();
    LOGGER.debug("computer.introduced: " + introStr);
    computerDto.setIntroduced(introStr);

    // Get discontinued date input
    System.out.println("Discontinued date (" + dateFormat + "): ");
    String discStr = sc.next();
    LOGGER.debug("computer.discontinued: " + discStr.toString());
    computerDto.setDiscontinued(discStr);

    // Get company input
    System.out.println("CompanyId ( must match existring one): ");
    long id = sc.nextLong();
    LOGGER.debug("company_id: {}", id);
    computerDto.setCompany(new CompanyDto(id, ""));

    // Create computer
    LOGGER.debug("Try to create computer: {}", computerDto);
    CliRequestManager.createComputer(computerDto);

  }

}
