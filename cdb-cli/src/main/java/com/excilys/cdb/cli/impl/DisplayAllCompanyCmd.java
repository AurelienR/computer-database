package com.excilys.cdb.cli.impl;

import com.excilys.cdb.cli.Command;
import com.excilys.cdb.models.Company;
import com.excilys.cdb.services.CompanyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
* CLI to display all companies
*
* @author Aurelien.R
*
*/
@Component
public class DisplayAllCompanyCmd implements Command {

  // Service
  @Autowired
  private CompanyService companyService;

  public DisplayAllCompanyCmd() {
  }

  @Override
  public void execute() {

    System.out.println("Display all companies...");
    for (Company c : companyService.findAll()) {
      System.out.println(c);
    }

  }

}
