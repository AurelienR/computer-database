package com.excilys.cdb.cli.impl;

import com.excilys.cdb.cli.CliException;
import com.excilys.cdb.cli.Command;
import com.excilys.cdb.cli.InputCommandParser;
import com.excilys.cdb.daos.DaoException;
import com.excilys.cdb.models.Company;
import com.excilys.cdb.models.Computer;
import com.excilys.cdb.services.ComputerService;
import com.excilys.cdb.services.ServiceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * CLI to add a computer
 * 
 * @author Aurelien.R
 *
 */
public class CreateComputerCmd implements Command {

  // Logger
  static final Logger logger = LoggerFactory.getLogger(CreateComputerCmd.class);

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
      String computerName = InputCommandParser.getRequiredNameInput(sc);
      logger.debug("computer.name: " + computerName);
      computer.setName(computerName);

      // Get introduced date input
      System.out.println("Introduced date (" + dateFormat + "): ");
      sc.nextLine();
      LocalDateTime introDate = InputCommandParser.getDateInput(sc, dateFormat);
      logger.debug("computer.introduced: " + introDate.toString());
      computer.setIntroduced(introDate);

      // Get discontinued date input
      System.out.println("Discontinued date (" + dateFormat + "): ");
      LocalDateTime discDate = InputCommandParser.getDateInput(sc, dateFormat);
      logger.debug("computer.discontinued: " + discDate.toString());
      computer.setDiscontinued(discDate);

      // Get company input
      System.out.println("CompanyName ( must match existring one): ");
      Company company = InputCommandParser.getRequiredValidCompanyByName(sc).get(0);
      logger.debug("computer.company: " + company.toString());
      computer.setCompany(company);

      // Create computer
      logger.debug("Try to create computer: " + computer);
      ComputerService.getInstance().createComputer(computer);

    } catch (ParseException e) {
      throw new CliException("Parsing exception", e);
    } catch (IllegalArgumentException e) {
      throw new CliException("Illegal argument", e);
    } catch (ServiceException e) {
      throw new CliException("Illegal argument", e);
    } catch (DaoException e) {
      throw new CliException("DAO exception", e);
    }

  }

}
