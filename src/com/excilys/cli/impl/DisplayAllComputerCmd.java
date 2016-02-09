package com.excilys.cli.impl;

import com.excilys.cli.Command;
import com.excilys.dao.DAOException;
import com.excilys.dao.ConnectionFactory;
import com.excilys.models.Computer;

public class DisplayAllComputerCmd implements Command {
	
	public DisplayAllComputerCmd(){};

	@Override
	public void execute() {
		System.out.println("Display all computers...");
		try{
			for(Computer c:ConnectionFactory.getInstance().getComputerDAO().findAll()){
				System.out.println(c);
			}
		}catch(DAOException e){
			System.out.println("Error cannot display computers");
			System.out.println("Message: "+ e.getMessage());
		}
	}
}
