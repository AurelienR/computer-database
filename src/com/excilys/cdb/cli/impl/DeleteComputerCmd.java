package com.excilys.cdb.cli.impl;

import java.util.Scanner;

import com.excilys.cdb.cli.Command;
import com.excilys.cdb.cli.InputCommandParser;
import com.excilys.cdb.models.Computer;
import com.excilys.cdb.services.ComputerService;

/**
 * CLI to delete a computer
 * @author Aurelien.R
 *
 */
public class DeleteComputerCmd implements Command{
	
	private Scanner sc;
	
	public DeleteComputerCmd(Scanner sc){
		this.sc = sc;
	}

	@Override
	public void execute() {
		
		System.out.println("Delete computer:");
		
		// Get computer name input
		System.out.println("Name (of existing computer):");
		sc.nextLine();
		Computer computer= InputCommandParser.getValidComputerByName(sc).get(0);
		
		// Delete computer
		ComputerService.getInstance().deleteComputer(computer.getId());
	}

}
