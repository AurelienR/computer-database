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

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;
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

  // Parser
  @Autowired
  private InputCommandParser inputCmdParser;
  // Service
  @Autowired
  private ComputerService computerService;

  private final String dateFormat = "dd/MM/yyyy";
  private Scanner sc;

  public UpdateComputerCmd(Scanner sc) {
    this.sc = sc;
  }

  @Override
  public void execute() {

    try {
      System.out.println("Update a computer:");

      // Get name input [required]
      System.out.println("Name of the computer to update:");
      sc.nextLine();
      Computer newComputer = inputCmdParser.getRequiredValidComputerByName(sc).get(0);
      LOGGER.debug("Computer retrieved: {}", newComputer);

      // Get new name input
      System.out.println("New Name:");
      String newName = inputCmdParser.getNameInput(sc);
      if (!newName.isEmpty()) {
        LOGGER.debug("computer.name: {}", newName);
        newComputer.setName(newName);
      }

      // Get
      System.out.println("Introduced (" + dateFormat + "): ");
      sc.nextLine();
      LocalDateTime introDate = inputCmdParser.getDateInput(sc, dateFormat);
      if (introDate != null) {
        LOGGER.debug("computer.introduced:{} ", introDate);
        newComputer.setIntroduced(introDate);
      }

      System.out.println("Discontinued (" + dateFormat + "): ");
      LocalDateTime discDate = inputCmdParser.getDateInput(sc, dateFormat);
      if (introDate != null) {
        LOGGER.debug("computer.discontinued: {}", discDate);
        newComputer.setIntroduced(discDate);
      }

      System.out.println("CompanyName ( must match existring one): ");
      List<Company> companies;
      if ((companies = inputCmdParser.getValidCompanyByName(sc)) != null && !companies.isEmpty()) {
        Company company = companies.get(0);
        LOGGER.debug("computer.company: {}", company);
        newComputer.setCompany(company);
      }

      LOGGER.debug("Try to update computer: {}", newComputer);
      computerService.updateComputer(newComputer);

    } catch (ParseException e) {
      throw new CliException("Parsing exception", e);
    } catch (IllegalArgumentException e) {
      throw new CliException("Illegal argument", e);
    } catch (DaoException e) {
      throw new CliException("DAO exception", e);
    }

  }

}
