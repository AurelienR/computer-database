package com.excilys.cdb.cli.impl;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Scanner;

import com.excilys.cdb.cli.Command;
import com.excilys.cdb.cli.InputCommandParser;
import com.excilys.cdb.dao.DAOException;
import com.excilys.cdb.models.Company;
import com.excilys.cdb.models.Computer;
import com.excilys.cdb.services.ComputerService;

/**
 * CLI to update a computer
 * @author Aurelien.R
 *
 */
public class UpdateComputerCmd implements Command{
	
	private final String dateFormat ="dd/MM/yyyy";
	private Scanner sc;
	
	public UpdateComputerCmd(Scanner sc){
		this.sc = sc;		
	}
	
	@Override
	public void execute() {
	
		try{
			System.out.println("Update a computer:");
			System.out.println("Name of the computer to update:");
			Computer newComputer = InputCommandParser.getValidComputerByName(sc).get(0);
			
			
			System.out.println("New Name:");
			String newName = InputCommandParser.getNameInput(sc);
			newComputer.setName(newName);
			
			System.out.println("Introduced ("+dateFormat+"): ");
			LocalDateTime introDate = InputCommandParser.getDateInput(sc, dateFormat);
			newComputer.setIntroduced(introDate);
			
			System.out.println("Discontinued ("+dateFormat+"): ");
			LocalDateTime discDate = InputCommandParser.getDateInput(sc, dateFormat);
			newComputer.setIntroduced(discDate);
			
			System.out.println("CompanyName ( must match existring one): ");
			Company company = InputCommandParser.getValidCompanyByName(sc).get(0);
			newComputer.setCompany(company);
			
			ComputerService.getInstance().updateComputer(newComputer);
			
			
		} catch (ParseException e) {
			System.out.println("Cannot parse date correctly");
			System.out.println("Message: "+ e.getMessage());
		} catch (IllegalArgumentException e){
			System.out.println("Invalide argument");
			System.out.println("Message: "+ e.getMessage());
		} catch (DAOException e){
			System.out.println("DAO issue");
			System.out.println("Message: "+ e.getMessage());
		}
		
	}
	

}
