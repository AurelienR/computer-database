package com.excilys.cdb.cli.impl;

import com.excilys.cdb.cli.Command;
import com.excilys.cdb.dao.DAOException;
import com.excilys.cdb.models.Computer;
import com.excilys.cdb.services.ComputerService;

/**
 * CLI to display all computers
 * @author Aurelien.R
 *
 */
public class DisplayAllComputerCmd implements Command {
	
	public DisplayAllComputerCmd(){};

	@Override
	public void execute() {

		try {
			System.out.println("Display all computers...");
			for(Computer c: ComputerService.getInstance().findAll()){
				System.out.println(c);
			}
		} catch(DAOException e){
			System.out.println("Error cannot display computers");
			System.out.println("Message: "+ e.getMessage());
		}
	}
}
