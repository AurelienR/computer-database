package com.excilys.cdb.cli.impl;

import com.excilys.cdb.cli.Command;
import com.excilys.cdb.dtos.CompanyDto;
import com.excilys.cdb.network.CliRequestManager;

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

    System.out.println("Display all companies...");
    for (CompanyDto c : CliRequestManager.getCompanies() ) {
      System.out.println(c);
    }

  }

}
