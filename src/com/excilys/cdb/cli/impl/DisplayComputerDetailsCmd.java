package com.excilys.cdb.cli.impl;

import java.util.Scanner;

import com.excilys.cdb.cli.Command;
import com.excilys.cdb.cli.InputCommandParser;
import com.excilys.cdb.dao.DAOException;
import com.excilys.cdb.models.Computer;

/**
 * CLI to display computer details
 * @author Aurelien.R
 *
 */
public class DisplayComputerDetailsCmd implements Command {
	
	private Scanner sc;
	
	public DisplayComputerDetailsCmd(Scanner sc) {
		this.sc = sc;
	};
	
	@Override
	public void execute() {
		try {
			System.out.println("Find computer details:");
			// Get computer name input
			System.out.println("Name (of existing computer):");				
			for(Computer c : InputCommandParser.getValidComputerByName(sc)){
				System.out.println(c);
			}
			
		} catch (IllegalArgumentException e){
			System.out.println("Invalide argument");
			System.out.println("Message: "+ e.getMessage());
		} catch (DAOException e){
			System.out.println("DAO issue");
			System.out.println("Message: "+ e.getMessage());
		}
	}
}
