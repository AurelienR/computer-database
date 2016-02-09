package com.excilys.cli.impl;

import com.excilys.cli.Command;
import com.excilys.dao.DAOException;
import com.excilys.dao.ConnectionFactory;
import com.excilys.models.Company;

public class DisplayAllCompanyCmd implements Command{

	public DisplayAllCompanyCmd(){};

	@Override
	public void execute() {
		System.out.println("Display all companies...");
		try{
			for(Company c:ConnectionFactory.getInstance().getCompanyDAO().findAll()){
				System.out.println(c);
			}
		}catch(DAOException e){
			System.out.println("Error cannot display companies");
			System.out.println("Message: "+ e.getMessage());
		}
	}

}
