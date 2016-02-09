package com.excilys.cli.impl;

import java.util.Scanner;

import com.excilys.cli.Command;
import com.excilys.dao.ConnectionFactory;
import com.excilys.models.Computer;

public class DeleteComputerCmd implements Command{
	
	private Scanner sc;
	
	public DeleteComputerCmd(Scanner sc){
		this.sc = sc;
	}

	@Override
	public void execute() {
		System.out.println("Delete computer:");
		System.out.println("Name (of existing computer):");
		String computerName;
		sc.nextLine();
		while((computerName =sc.nextLine()).isEmpty() || ConnectionFactory.getInstance().getComputerDAO().findByName(computerName).isEmpty()){
			System.out.println("Computer Name is not matching with an existring one");
		}
		
		if(computerName.isEmpty())return;
		
		for(Computer c : ConnectionFactory.getInstance().getComputerDAO().findByName(computerName)){
			ConnectionFactory.getInstance().getComputerDAO().deleteComputer(c.getId());
			System.out.println("Delete:   "+ c);
		}
		
	}

}
