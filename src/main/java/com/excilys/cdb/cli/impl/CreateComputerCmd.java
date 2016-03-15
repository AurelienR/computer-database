package com.excilys.cdb.cli.impl;

import com.excilys.cdb.cli.CliException;
import com.excilys.cdb.cli.Command;
import com.excilys.cdb.cli.InputCommandParser;
import com.excilys.cdb.daos.DaoException;
import com.excilys.cdb.models.Company;
import com.excilys.cdb.models.Computer;
import com.excilys.cdb.services.ComputerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.LocalDateTime;
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

  // Parser
  @Autowired
  private InputCommandParser inputCmdParser;
  // Service
  @Autowired
  private ComputerService computerService;

  private final String dateFormat = "dd/MM/yyyy";
  private Scanner sc;

  public CreateComputerCmd(Scanner sc) {
    this.sc = sc;
  }

  @Override
  public void execute() throws CliException {
    Computer computer = new Computer();

    try {
      System.out.println("Create a computer:");

      // Get name input
      System.out.println("Name:");
      String computerName = inputCmdParser.getRequiredNameInput(sc);
      LOGGER.debug("computer.name: " + computerName);
      computer.setName(computerName);

      // Get introduced date input
      System.out.println("Introduced date (" + dateFormat + "): ");
      sc.nextLine();
      LocalDateTime introDate = inputCmdParser.getDateInput(sc, dateFormat);
      LOGGER.debug("computer.introduced: " + introDate.toString());
      computer.setIntroduced(introDate);

      // Get discontinued date input
      System.out.println("Discontinued date (" + dateFormat + "): ");
      LocalDateTime discDate = inputCmdParser.getDateInput(sc, dateFormat);
      LOGGER.debug("computer.discontinued: " + discDate.toString());
      computer.setDiscontinued(discDate);

      // Get company input
      System.out.println("CompanyName ( must match existring one): ");
      Company company = inputCmdParser.getRequiredValidCompanyByName(sc).get(0);
      LOGGER.debug("computer.company: {}", company);
      computer.setCompany(company);

      // Create computer
      LOGGER.debug("Try to create computer: {}", computer);
      computerService.createComputer(computer);

    } catch (ParseException e) {
      throw new CliException("Parsing exception", e);
    } catch (IllegalArgumentException e) {
      throw new CliException("Illegal argument", e);
    } catch (DaoException e) {
      throw new CliException("DAO exception", e);
    }

  }

}
