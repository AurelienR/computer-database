package com.excilys.cdb.cli.impl;

import com.excilys.cdb.cli.CliException;
import com.excilys.cdb.cli.Command;
import com.excilys.cdb.daos.DaoException;
import com.excilys.cdb.models.Company;
import com.excilys.cdb.services.CompanyService;
import com.excilys.cdb.services.ServiceException;

/**
 * CLI to display all companies
 * 
 * @author Aurelien.R
 *
 */
public class DisplayAllCompanyCmd implements Command {

  public DisplayAllCompanyCmd() {
  }

  @Override
  public void execute() {
    try {
      System.out.println("Display all companies...");
      for (Company c : CompanyService.getInstance().findAll()) {
        System.out.println(c);
      }
    } catch (ServiceException e) {
      throw new CliException("Illegal argument", e);
    } catch (DaoException e) {
      throw new CliException("DAO exception", e);
    }
  }

}
